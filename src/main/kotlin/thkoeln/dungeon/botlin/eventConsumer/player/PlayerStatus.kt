package thkoeln.dungeon.botlin.eventConsumer.player

import com.fasterxml.jackson.annotation.JsonProperty

enum class PlayerStatus {
    @JsonProperty("joined")
    JOINED,
    ORPHANED
}