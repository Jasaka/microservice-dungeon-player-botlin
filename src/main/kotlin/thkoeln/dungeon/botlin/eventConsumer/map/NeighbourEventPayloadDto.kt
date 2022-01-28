package thkoeln.dungeon.botlin.eventConsumer.map

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class NeighbourEventPayloadDto{
    @JsonProperty("planetId")
    private lateinit var planetId: UUID
    @JsonProperty("movementDifficulty")
    private var movementDifficulty: Int = 0;
    @JsonProperty("direction")
    private lateinit var direction: Direction

    fun getPlanetId () = planetId
    fun getMovementDiff() = movementDifficulty
    fun getDirection()= direction

    companion object
    {
        fun fromJsonString(jsonString: String) : NeighbourEventPayloadDto
        {
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, NeighbourEventPayloadDto::class.java)
        }
    }
}