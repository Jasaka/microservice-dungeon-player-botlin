package thkoeln.dungeon.botlin.game.domain

import com.fasterxml.jackson.annotation.JsonProperty

enum class GameStatus {
    @JsonProperty("created")
    CREATED,
    @JsonProperty("started")
    RUNNING,
    @JsonProperty("ended")
    FINISHED,
    ORPHANED // this is the state a game takes when the GameService doesn't list it anymore
}
