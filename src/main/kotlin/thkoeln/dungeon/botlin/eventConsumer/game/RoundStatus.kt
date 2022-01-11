package thkoeln.dungeon.botlin.eventConsumer.game

import com.fasterxml.jackson.annotation.JsonProperty

enum class RoundStatus {
    @JsonProperty("started")
    STARTED,
    @JsonProperty("command input ended")
    COMMAND_END,
    @JsonProperty("ended")
    ROUND_END

}