package thkoeln.dungeon.botlin.robot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.player.Player
import thkoeln.dungeon.botlin.player.PlayerRepository
import java.util.*

@Service
class RobotApplicationService {
   var robotRepository : RobotRepository

   @Autowired
   constructor(robotRepository: RobotRepository)
   {
       this.robotRepository = robotRepository
   }


}