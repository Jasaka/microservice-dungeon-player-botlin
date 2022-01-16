package thkoeln.dungeon.botlin.api.exceptions

open class BotlinException (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}