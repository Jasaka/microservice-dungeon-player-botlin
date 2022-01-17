package thkoeln.dungeon.botlin.command

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import java.util.*

@Setter
@Getter
@NoArgsConstructor
@ToString

class CommandObject {
    private lateinit var commandType: CommandType

    private lateinit var planetId : UUID

    private lateinit var targetId  : UUID

    private lateinit var itemName: String

    private var itemQuantity: Int = 0
}