package thkoeln.dungeon.botlin.eventConsumer.game

import org.springframework.data.repository.CrudRepository
import java.util.*

interface GameStatusEventRepository : CrudRepository<GameStatusEvent, UUID>{
}