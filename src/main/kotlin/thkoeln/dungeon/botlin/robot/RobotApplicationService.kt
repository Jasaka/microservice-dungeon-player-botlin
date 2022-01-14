package thkoeln.dungeon.botlin.robot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.player.Player
import thkoeln.dungeon.botlin.player.PlayerApplicationService
import thkoeln.dungeon.botlin.player.PlayerRepository
import java.util.*

@Service
class RobotApplicationService {
    var robotRepository: RobotRepository
    var playerApplicationService: PlayerApplicationService

    @Autowired
    constructor(robotRepository: RobotRepository, playerApplicationService: PlayerApplicationService) {
        this.robotRepository = robotRepository
        this.playerApplicationService = playerApplicationService
    }


    fun assignRobotToPlayer(robotPayloadDto: RobotPayloadDto, playerId: UUID) {
        var robot: Robot? = null
        try {
            robot = robotRepository.findById(robotPayloadDto.id).get()
        } catch (e: NoSuchElementException) {
            //create robot from provided data
            robot = Robot(robotPayloadDto)
        }
        playerApplicationService.assignRobotToPlayer(robot!!, playerId)
        robotRepository.save(robot)
    }

    fun updateRobot(robotPayloadDto: RobotPayloadDto) {
        var robot = Robot(robotPayloadDto)
        robotRepository.save(robot)

    }

    fun move(robotId: UUID) {
        var robot = robotRepository.findById(robotId).get()
        //todo MOVE

    }

    fun buy(robotId: UUID) {
        var robot = robotRepository.findById(robotId).get()
        //todo BUY STUFF

    }

    fun sell(robotId: UUID) {
        var robot = robotRepository.findById(robotId).get()
        //todo SELL STUFF

    }

    fun mine(robotId: UUID) {
        var robot = robotRepository.findById(robotId).get()
        //todo MINE

    }

    fun regenerate(robotId: UUID) {
        var robot = robotRepository.findById(robotId).get()
        //todo REGENERATE

    }

}

