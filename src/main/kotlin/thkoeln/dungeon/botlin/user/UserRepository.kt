package thkoeln.dungeon.botlin.user

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID>{
    override fun findAll(): List<User>;
}