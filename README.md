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

Using the library requires an OpenAI API Key!

If you do not have an api key already, follow these steps:
1. Create an account at https://beta.openai.com/
2. Navigate to the API Keys [page](https://beta.openai.com/account/api-keys)
3. Create a new secret key

The following is a simple example to fetch the details of a model

```kotlin
val client = OpenAiClient.Builder(apiKey = "<YOUR KEY HERE>") {
    // optional configurations
}

val model = client.api.retrieveModel("text-davinci-003")
assertEquals("text-davinci-003", model.id)
```

## Examples

## Android

### Setup
1. Add the following environment variable `OPEN_AI_API_KEY=<YOUR KEY HERE>`
2. Run gradle sync (`./gradlew sync`)
3. Run application

If the following error occurs (`OPEN_AI_API_KEY cannot be null`), ensure the following:
1. Environment variable is persisted across shell instances
2. , Restart IDE to ensure environment variable changes are applied

## iOS

TBA

## Tests

The `commonTest` contains tests that will execute real requests to the OpenAI API.

### Setup

1. Navigate to `commonTest/kotlin/com.henryxu.openaikotlin`
2. Find the `PrivateInfo.kt` file
3. Replace `<YOUR KEY HERE>` with your API Key 

**NOTE**:

Ensure that if you make changes, this file is not committed.
This git method was executed, so future changes should by default not be included:

```shell
git update-index --assume-unchanged shared/src/commonTest/kotlin/com/henryxu/openaikotlin/PrivateInfo.kt
```
