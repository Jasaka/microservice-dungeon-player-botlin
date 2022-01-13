package thkoeln.dungeon.botlin.user

import thkoeln.dungeon.botlin.game.domain.Game
import java.util.*
import javax.persistence.*

@Entity
class User {
    @Id
    var id = UUID.randomUUID()

    //TODO bind to front end
    lateinit var name : String
    lateinit var email : String
    lateinit var bearerToken : UUID

    @OneToMany(cascade = [CascadeType.MERGE,CascadeType.REMOVE], fetch = FetchType.EAGER)
    var games : List<Game> = arrayListOf()
    fun isReadyToPlay() : Boolean = bearerToken != null

    fun participateInGame(game : Game)
    {
        game.

    }
}