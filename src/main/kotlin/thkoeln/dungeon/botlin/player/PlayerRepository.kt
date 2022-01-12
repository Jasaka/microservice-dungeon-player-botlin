package thkoeln.dungeon.botlin.player

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import thkoeln.dungeon.botlin.game.domain.Game
import java.util.*
interface PlayerRepository : CrudRepository<Player,UUID>{
    override fun findAll(): List<Player>;
}