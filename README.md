# OpenAI Kotlin Multiplatform Library

The Unofficial OpenAI Kotlin library provides convenient access to the OpenAI API for multiple languages 
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

Next is a more complex example of streaming the results of a completion request

```kotlin
val model = "text-davinci-003"
val request = CreateCompletionRequest(
    model,
    prompt = "Say this is a test",
    logprobs = null,
    stop = listOf("\n")
)

val result = client.api.streamCreateCompletion(request)
CoroutineScope(Dispatchers.IO).launch {
    result.collect { completion ->
        // ... do stuff here
    }
}
```

## Examples

## Android

### Setup

1. Navigate to the `android` module
2. Make a copy of the `.env.template` file and name it `.env`
3. Populate the required values for each key in `.env`
   - NOTE: Do **NOT** rename any keys!
   - NOTE: Do **NOT** commit the `.env` file!
4. Run application

## iOS

TBA

## NodeJS

### Setup

1. Navigate to the `web` module
2. Make a copy of the `.env.template` file and name it `.env`
3. Populate the required values for each key in `.env` 
   - NOTE: Do **NOT** rename any keys (they must be prefixed with `REACT_APP`)!
   - NOTE: Do **NOT** commit the `.env` file!
4. Execute `npm run start`

To access variables of type `Long` (e.g `created` / `createdAt`), you must access the `a1_1`
of the variable.

```javascript
    data.created.a1_1
```

This is simply a result of `kotlin` not being able to export the `Long` type to `JS` yet.

## Tests

The `commonTest` contains tests that will execute real requests to the OpenAI API.

### Setup

1. Navigate to the `shared` module
2. Navigate to `commonTest/kotlin/com/henryxu/openaikotlin`
3. Make a copy of the `PrivateInfo.kt.template` file and name it `PrivateInfo.kt`
4. Populate the required values for each field of the `PrivateInfo` object in `PrivateInfo.kt`
   - NOTE: Do **NOT** rename any variables!
   - NOTE: Do **NOT** commit the `PrivateInfo.kt` file!
5. Run the desired tests via `./gradlew`

It seems unconventional to use a Kotlin class to store credentials for tests,
but the reason is that it is difficult to find a platform-agnostic way to load variables
from either a file or the environment.

# References
- [OpenAI API Spec](https://github.com/openai/openai-openapi)