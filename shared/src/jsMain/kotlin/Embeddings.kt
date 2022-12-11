import com.henrymxu.openaikotlin.models.CreateEmbeddingsRequest
import com.henrymxu.openaikotlin.models.EmbeddingData
import com.henrymxu.openaikotlin.models.EmbeddingResult
import com.henrymxu.openaikotlin.models.Usage

@JsExport
@JsName("CreateEmbeddingRequest")
external interface CreateEmbeddingsRequestWrapper {
    val model: String
    val input: String
    val user: String?
}

fun CreateEmbeddingsRequestWrapper.unwrap() = CreateEmbeddingsRequest(model, input, user)

//@JsExport
//@JsName("CreateEmbeddingRequest")
//data class CreateEmbeddingsRequestWrapper(
//    val model: String,
//    val input: String,
//    val user: String? = null,
//): RequestWrapper<CreateEmbeddingsRequest> {
//    @Suppress("NON_EXPORTABLE_TYPE")
//    override fun unwrap() = CreateEmbeddingsRequest(input, model, user)
//}

@JsExport
@JsName("EmbeddingResult")
data class EmbeddingResultWrapper(
    val type: String,
    val data: Array<EmbeddingDataWrapper>,
    val usage: Usage
)

fun EmbeddingResult.wrap(): EmbeddingResultWrapper {
    return EmbeddingResultWrapper(type, data.map { it.wrap() }.toTypedArray(), usage)
}

@JsExport
@JsName("EmbeddingData")
data class EmbeddingDataWrapper(
    val type: String,
    val embedding: Array<Float>,
    val index: Int
)

fun EmbeddingData.wrap(): EmbeddingDataWrapper {
    return EmbeddingDataWrapper(type, embedding.toTypedArray(), index)
}
