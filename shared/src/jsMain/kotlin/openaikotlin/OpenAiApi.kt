package openaikotlin

import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.OpenAiClientRequestError
import openaikotlin.services.CompletionsApiWrapper
import openaikotlin.services.EditsApiWrapper
import openaikotlin.services.EmbeddingsApiWrapper
import openaikotlin.services.EnginesApiWrapper
import openaikotlin.services.FilesApiWrapper
import openaikotlin.services.FineTunesApiWrapper
import openaikotlin.services.ImagesApiWrapper
import openaikotlin.services.ModelsApiWrapper
import openaikotlin.services.ModerationsApiWrapper

@JsExport
@JsName("OpenAiApi")
interface OpenAiApiWrapper: ModelsApiWrapper, CompletionsApiWrapper, EditsApiWrapper, ImagesApiWrapper,
    EmbeddingsApiWrapper, FilesApiWrapper, FineTunesApiWrapper, ModerationsApiWrapper, EnginesApiWrapper

@JsExport
@JsName("Response")
data class ResponseWrapper<T>(
    val result: T?,
    val raw: String?,
    val error: OpenAiClientRequestError?,
)

fun <T, R> Response<R>.wrapResponse(wrap: (R?) -> T?): ResponseWrapper<T> {
    return ResponseWrapper(wrap(result), raw, error)
}