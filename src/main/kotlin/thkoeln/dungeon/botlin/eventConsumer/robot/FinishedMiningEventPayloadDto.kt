package thkoeln.dungeon.botlin.eventConsumer.robot

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class FinishedMiningEventPayloadDto {
    @JsonProperty("planet_id")
    private lateinit var planetId: UUID

    @JsonProperty("resource_id")
    private lateinit var resourceId: UUID

    @JsonProperty("resource_type")
    private var resourceType: String = ""

    @JsonProperty("amount_mined")
    private var amountMined = -1

    @JsonProperty("amount_left")
    private var amountLeft = -1

    fun getPlanetId() = planetId
    fun getResourceId() = resourceId
    fun getResourceType() = resourceType
    fun getAmountMined() = amountMined
    fun getAmountLeft() = amountLeft

    companion object {
        fun fromJsonString(jsonString: String): FinishedMiningEventPayloadDto {
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, FinishedMiningEventPayloadDto::class.java)

        }
    }
}
