import com.henrymxu.openaikotlin.Response
import com.henrymxu.openaikotlin.models.OpenAiClientRequestError
import services.CompletionsApiWrapper
import services.EditsApiWrapper
import services.EmbeddingsApiWrapper
import services.EnginesApiWrapper
import services.FilesApiWrapper
import services.FineTunesApiWrapper
import services.ImagesApiWrapper
import services.ModelsApiWrapper
import services.ModerationsApiWrapper

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