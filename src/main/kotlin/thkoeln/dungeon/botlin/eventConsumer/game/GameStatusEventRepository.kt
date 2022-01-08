package thkoeln.dungeon.botlin.eventConsumer.game

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

interface GameStatusEventRepository : CrudRepository<GameStatusEvent, UUID>{
}