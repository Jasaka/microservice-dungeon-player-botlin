package thkoeln.dungeon.botlin.player

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.game.application.GameApplicationService
import thkoeln.dungeon.botlin.game.domain.Game
import thkoeln.dungeon.botlin.user.User
import thkoeln.dungeon.botlin.user.UserRepository
import java.util.*

@Service
class PlayerApplicationService {
    private var playerRepository: PlayerRepository?;
    private var gameApplicationService: GameApplicationService?
    private var userRepository: UserRepository?

    @Autowired
    constructor(playerRepository: PlayerRepository, gameApplicationService: GameApplicationService, userRepository: UserRepository) {
        this.playerRepository = playerRepository
        this.gameApplicationService = gameApplicationService
        this.userRepository = userRepository
    }

    fun joinAllInNewGame(gameId: UUID) {
        var game = gameApplicationService!!.gameExternallyCreated(gameId);
        var users= userRepository!!.findAll()
        for (user in users) registerOneUserForGame(user, game)
    }

    fun registerOneUserForGame(user: User, game: Game) {
        //TODO check user bearer token
        //Todo if userStatus = JOINED create a PLAYER for game !
        if(user.bearerToken== null){
           obtainBearerTokenForUser(user)
        }
        //TODO boolean = Rest.registerPlayerForGame
        if (user.isReadyToPlay())
        {
            userRepository?.save(user)
            gameApplicationService!!.newUserJoinedGame(user, game)
        }
    }


    fun obtainBearerTokenForUser(user: User) {
        if (user.isReadyToPlay()) {

        }
    }

    fun obtainBearerTokensForAllUsers() {
        var users: List<User>? = userRepository?.findAll()
        if (users != null) {
            for (user in users) obtainBearerTokenForUser(user)
        }
    }

}