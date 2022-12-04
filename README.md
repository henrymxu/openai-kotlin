# OpenAI Kotlin Multiplatform Library

The OpenAI Kotlin library provides convenient access to the OpenAI API for multiple languages 
via [Kotlin Multiplatform Module](https://kotlinlang.org/docs/multiplatform.html). 

This library contains support for the following platforms:

- Android
- iOS
- JVM
- JS

It includes statically defined types for both requests and responses for all the publicly documented APIs.

## Usage

```kotlin
val client = OpenAiClient.Builder(apiKey = "<YOUR KEY HERE>") {
    // optional configurations
}

val model = client.api.retrieveModel("text-davinci-003")
assertEquals("text-davinci-003", model.id)
```

## Examples

### Android

In Progress

### iOS

TBA

## Tests

The `commonTest` contains tests that will execute real requests to the OpenAI API.

### Setup

1. Navigate to `commonTest/kotlin/com.henryxu.openaikotlin`
2. Find the `PrivateInfo.kt` file
3. Replace `<YOUR KEY HERE>` with your API Key 

If you do not have an api key already, follow these steps:
1. Create an account at https://beta.openai.com/
2. Navigate to the API Keys [page](https://beta.openai.com/account/api-keys)
3. Create a new secret key
