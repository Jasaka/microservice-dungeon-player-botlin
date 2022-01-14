package thkoeln.dungeon.botlin.game.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
class Game(){

    constructor(gameDto: GameDto) : this() {
        this.gameID = gameDto.gameID
        this.gameStatus = gameDto.gameStatus
        this.currentRoundCount = gameDto.currentRoundCount
    }

    @Id
    private var id = UUID.randomUUID();

    lateinit var gameID: UUID
    lateinit var gameStatus: GameStatus
    //TODO lateinit var player : Player
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