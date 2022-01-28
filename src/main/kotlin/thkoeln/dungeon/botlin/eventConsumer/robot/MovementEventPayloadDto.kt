package thkoeln.dungeon.botlin.eventConsumer.robot

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

class MovementEventPayloadDto {

    @JsonProperty("success")
    private var success = true

    @JsonProperty("message")
    private var message = ""

    @JsonProperty("remainingEnergy")
    private var remainingEnergy = 0

    @JsonProperty("planet")
    private lateinit var planet: Planet

    @JsonProperty("robots")
    private var robots = mutableListOf<String>()

    fun getSuccess() = success
    fun getMessage() = message
    fun getRemainingEnergy() = remainingEnergy
    fun getPlanet() = planet
    fun getRobots() = robots

    companion object {
        fun fromJsonString(jsonString: String): MovementEventPayloadDto {
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, MovementEventPayloadDto::class.java)

        }
    }
}
