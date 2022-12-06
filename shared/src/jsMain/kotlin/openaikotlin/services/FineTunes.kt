package openaikotlin.services

import com.henryxu.openaikotlin.models.EntityDeleteResult
import openaikotlin.ResponseWrapper
import openaikotlin.CreateFineTuneRequestWrapper
import openaikotlin.FineTuneEventResultWrapper
import openaikotlin.FineTuneEventsResultWrapper
import openaikotlin.FineTuneResultWrapper
import openaikotlin.FineTunesResultWrapper
import kotlin.js.Promise

/**
 * @see com.henryxu.openaikotlin.services.FineTunesApi
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
