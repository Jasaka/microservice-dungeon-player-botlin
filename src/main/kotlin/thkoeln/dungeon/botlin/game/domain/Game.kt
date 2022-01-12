package thkoeln.dungeon.botlin.game.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thkoeln.dungeon.botlin.player.Player
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
class Game {

    @Id
    private var id = UUID.randomUUID();

    lateinit var gameID: UUID
    lateinit var gameStatus: GameStatus
    @OneToMany()
    var players : MutableList<Player> = mutableListOf()
    var currentRoundCount = -1

    @Transient
    var logger: Logger = LoggerFactory.getLogger(Game::class.java)

    fun resetToNewlyCreated() {
        gameStatus = GameStatus.CREATED
        currentRoundCount = 0
        logger.warn("Reset game $this to CREATED!")
    }

    fun makeOrphan() {
        gameStatus = GameStatus.ORPHANED
        logger.warn("Marked Game $this as ORPHANED!")
    }
    fun getParticipants() : List<Player> = players


    companion object {
        fun newlyCreatedGame(gameId: UUID): Game {
            var game = Game();
            game.gameID = gameId
            game.resetToNewlyCreated()
            return game
        }
    }

    @Override
    override fun equals(other: Any?): Boolean {
        return if (this === other) {
            true
        } else if (other == null || javaClass != other.javaClass) {
            false
        } else {
            var game = other as Game;
            id.equals(game.id)
        }

    }


    @Override
    override fun hashCode(): Int = Objects.hash(id);

}