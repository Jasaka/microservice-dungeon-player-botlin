package thkoeln.dungeon.botlin.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class TransactionIdResponseDto() {
    @JsonProperty("transactionId")
    var transactionId: UUID? = null

    constructor(transactionId: UUID) : this() {
        this.transactionId = transactionId
    }

    companion object{
        fun fromJsonString(jsonString: String?): TransactionIdResponseDto{
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, TransactionIdResponseDto::class.java)

        }
    }

    fun isValid(): Boolean = transactionId != null
}