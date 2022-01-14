package thkoeln.dungeon.botlin.robot

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*
import javax.persistence.Id

@JsonIgnoreProperties(ignoreUnknown = true)
class RobotPayloadDto(){

    @JsonProperty("id")
    var id = UUID.randomUUID()

    //TODO
    //var resources : MutableMap<Resource,Int> = mutableMapOf()
    //var items : MutableMap<Item,Int> = mutableMapOf()
    @JsonProperty("energy")
    var energy = 0
    @JsonProperty("level")
    var level = 1
    @JsonProperty("health")
    var health = 1
    @JsonProperty("currentPlanet")
    lateinit var currentPlanet: UUID

    companion object{
        fun fromJsonString(jsonString: String): RobotPayloadDto{
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, RobotPayloadDto::class.java)

        }
    }

    constructor(id: UUID, energy: Int, level: Int, health: Int,
                currentPlanet: UUID) : this() {
        this.id = id
        this.energy = energy
        this.level = level
        this.health = health
        this.currentPlanet = currentPlanet
    }

}
