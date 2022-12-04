package com.henryxu.openaikotlin

import java.io.File

/** Read the given resource as binary data. */
actual fun readImageResource(
    imageName: String
): ByteArray {
    return File("./src/commonTest/resources/${imageName}").readBytes()
}

