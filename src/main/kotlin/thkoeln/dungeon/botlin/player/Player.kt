package thkoeln.dungeon.botlin.player

import thkoeln.dungeon.botlin.eventConsumer.player.PlayerStatus
import thkoeln.dungeon.botlin.robot.Robot
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Player() {
    @Id
    lateinit var playerId: UUID;

    var moneten = 0

    //TODO var Robot
    @OneToMany
    var robots: MutableList<Robot> = mutableListOf()

    fun setMode() {
        //TODO Strategy pattern
    }

    constructor(registrationId: UUID) : this() {
        playerId = registrationId
        moneten = 200
    }

}