package thkoeln.dungeon.botlin.command

import com.fasterxml.jackson.annotation.JsonProperty

enum class CommandType {
    @JsonProperty("blocking")
    BLOCKING,

    @JsonProperty("buying")
    BUYING,

    @JsonProperty("selling")
    SELLING,

    @JsonProperty("movement")
    MOVEMENT,

    @JsonProperty("battle")
    BATTLE,

    @JsonProperty("mining")
    MINING,

    @JsonProperty("regeneration")
    REGENERATION,

    @JsonProperty("battleItemUse")
    BATTLEITEMUSE,

    @JsonProperty("repairItemUse")
    REPAIRITEMUSE,

    @JsonProperty("moveItemUse")
    MOVEITEMUSE,
}