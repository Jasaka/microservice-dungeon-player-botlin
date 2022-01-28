package thkoeln.dungeon.botlin.eventConsumer.robot

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class SpawnNotificationEventPayloadDto {

    @JsonProperty("robotId")
    private lateinit var robotId : UUID
    @JsonProperty("playerId")
    private lateinit var playerId: UUID
    @JsonProperty("otherSeeableRobots")
    private lateinit var robotsInSight: MutableList<UUID>

    fun getRobotId() = robotId
    fun getPlayerId() = playerId
    fun getRobotsInSight() = robotsInSight

    companion object{
        fun fromJsonString(jsonString: String): SpawnNotificationEventPayloadDto{
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, SpawnNotificationEventPayloadDto::class.java)

        }
    }
}
