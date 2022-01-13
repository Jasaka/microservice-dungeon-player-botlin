package thkoeln.dungeon.botlin.user

import thkoeln.dungeon.botlin.game.domain.Game
import thkoeln.dungeon.botlin.player.Player
import java.util.*
import javax.persistence.*
import kotlin.jvm.Throws

@Entity
class User() {
    @Id
    var id = UUID.randomUUID()

    //TODO bind to front end
    lateinit var name: String
    lateinit var email: String
    lateinit var bearerToken: UUID

    @OneToMany(cascade = [CascadeType.MERGE, CascadeType.REMOVE], fetch = FetchType.EAGER)
    var players: MutableList<Player> = arrayListOf()
    fun isReadyToPlay(): Boolean = bearerToken != null

    fun participate() : Player {
        var player = Player()
        players.add(player)
        return player
    }

    fun findPlayerInGame(game: Game): Boolean {
        for (player in players) {
            for (playerGame in game.players) {
                return playerGame == player
            }
        }
        return false;
    }

    @Override
    override fun toString(): String = "Player '$name' (bearerToke: $bearerToken)"
}