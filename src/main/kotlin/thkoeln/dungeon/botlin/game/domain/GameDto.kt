package thkoeln.dungeon.botlin.game.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.ToString
import java.util.*

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
class GameDto() {

    lateinit var gameID: UUID
    lateinit var gameStatus: GameStatus

    //TODO lateinit var player : Player
    var currentRoundCount = -1

    constructor(gameID: UUID, gameStatus: GameStatus, currentRoundCount: Int) : this() {
        this.gameID = gameID
        this.gameStatus = gameStatus
        this.currentRoundCount = currentRoundCount
    }
}