package thkoeln.dungeon.botlin.eventConsumer.robot

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

class MiningEventPayloadDto {
    @JsonProperty("success")
    private var success = true
    @JsonProperty("message")
    private var message = ""
    @JsonProperty("remainingEnergy")
    private var remainingEnergy = 0
    @JsonProperty("updatedInventory")
    private var updatedInventory = 0
    @JsonProperty("resourceType")
    private var resource = "coal"

    fun getSuccess() = success
    fun getMessage() = message
    fun getRemainingEnergy() = remainingEnergy
    fun getNewInventory() = updatedInventory
    fun getResource()= resource

    companion object{
        fun fromJsonString(jsonString: String): MiningEventPayloadDto{
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, MiningEventPayloadDto::class.java)

        }
    }
}
