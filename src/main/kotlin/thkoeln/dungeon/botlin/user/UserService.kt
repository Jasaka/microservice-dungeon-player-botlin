package thkoeln.dungeon.botlin.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    lateinit var userRepository: UserRepository

    @Autowired
    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }
}