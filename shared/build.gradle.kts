@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    id(libs.plugins.kotlin.native.cocoapods.get().pluginId)
    alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        moduleName = "openaikotlin"
        compilations["main"].packageJson {
            customField("hello", mapOf("one" to 1, "two" to 2))
        }
        browser {
            webpackTask {
                cssSupport.enabled = true
                // outputFileName = "openaikotlin.js"
                output.libraryTarget = "commonjs2"
            }
            testTask {
                useMocha {
                    timeout = "10000"
                }
            }
        }
        binaries.executable()
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    android()
    ios()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "shared"
            isStatic = true // Set it up explicitly because the default behavior will be changed to DYNAMIC linking in the 1.8 version.
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.common.kotlin)
                implementation(libs.bundles.common.ktor)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.bundles.common.test)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.bundles.java.main)
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation(libs.bundles.js.main)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val nativeMain by getting
        val nativeTest by getting
        val androidMain by getting {
            dependencies {
                implementation(libs.bundles.android.main)
            }
        }
        val androidTest by getting
        val iosMain by getting {
            dependencies {
                implementation(libs.bundles.ios.main)
            }
        }
        val iosTest by getting
    }
}

android {
    namespace = "com.henryxu.openaikotlin"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}
