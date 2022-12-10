package internal

import kotlin.js.Json

//@JsExport
//interface RequestWrapper<T> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    fun unwrap(): T
//}

fun<T> Json.toMap(): Map<String, T> {
    return (js("Object.entries") as (dynamic) -> Array<Array<T>>)
        .invoke(this).associate { entry -> entry[0] as String to entry[1] }
}
