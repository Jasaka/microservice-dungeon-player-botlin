package thkoeln.dungeon.botlin.robot

import org.springframework.data.repository.CrudRepository
import java.util.*

interface RobotRepository : CrudRepository<Robot, UUID>{
}