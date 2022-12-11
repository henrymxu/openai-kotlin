package services

import com.henrymxu.openaikotlin.models.EntityDeleteResult
import ResponseWrapper
import CreateFineTuneRequestWrapper
import FineTuneEventResultWrapper
import FineTuneEventsResultWrapper
import FineTuneResultWrapper
import FineTunesResultWrapper
import kotlin.js.Promise

/**
 * @see com.henrymxu.openaikotlin.services.FineTunesApi
 */
@JsExport
@JsName("FineTunesApi")
interface FineTunesApiWrapper {
    fun createFineTune(request: CreateFineTuneRequestWrapper): Promise<ResponseWrapper<FineTuneResultWrapper>>
    fun listFineTunes(): Promise<ResponseWrapper<FineTunesResultWrapper>>
    fun retrieveFineTune(fineTuneId: String): Promise<ResponseWrapper<FineTuneResultWrapper>>
    fun cancelFineTune(fineTuneId: String): Promise<ResponseWrapper<FineTuneResultWrapper>>
    fun listFineTuneEvents(
        fineTuneId: String
    ): Promise<ResponseWrapper<FineTuneEventsResultWrapper>>

    fun streamFineTuneEvents(
        fineTuneId: String,
        handler: (ResponseWrapper<FineTuneEventResultWrapper>) -> Unit
    )

    fun deleteFineTuneModel(modelId: String): Promise<ResponseWrapper<EntityDeleteResult>>
}
