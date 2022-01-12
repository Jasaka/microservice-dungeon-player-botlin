package thkoeln.dungeon.botlin.game.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

interface GameRepository : CrudRepository<Game, UUID> {
    fun findByGameID(gameId: UUID): List<Game>;
    fun existsByGameID(gameId: UUID): Boolean
    fun findAllByGameStatusEquals(gameStatus: GameStatus): List<Game>
    fun findAllByGameStatusBetween(gameStatus1: GameStatus, gameStatus2: GameStatus): List<Game>
    fun findFirstByGameStatusEquals(gameStatus: GameStatus): Game
    override fun findAll(): List<Game>;
}