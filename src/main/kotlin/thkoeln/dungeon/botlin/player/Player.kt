package thkoeln.dungeon.botlin.player

import thkoeln.dungeon.botlin.eventConsumer.player.PlayerStatus
import thkoeln.dungeon.botlin.robot.Robot
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Player {
    @Id
    var playerId: UUID = UUID.randomUUID()

    var moneten = 0

    //TODO var Robot
    @OneToMany
    var robots : MutableList<Robot> = mutableListOf()

    fun setMode() {
        //TODO Strategy pattern
    }

    constructor()
    {

    }

}