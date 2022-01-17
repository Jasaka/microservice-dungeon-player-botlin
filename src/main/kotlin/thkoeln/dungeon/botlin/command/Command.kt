package thkoeln.dungeon.botlin.command

interface  Command {
    var commandType: CommandType
    fun execute()


    class BlockCommand: Command{
        override var commandType = CommandType.BLOCKING
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    class BuyCommand: Command{
        override var commandType = CommandType.BUYING
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    class SellCommand: Command{
        override var commandType = CommandType.SELLING
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    class MoveCommand: Command{
        override var commandType = CommandType.MOVEMENT
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    class BattleCommand: Command{
        override var commandType = CommandType.BATTLE
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    class MineCommand: Command{
        override var commandType = CommandType.MINING
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    class RegenerateCommand: Command{
        override var commandType = CommandType.REGENERATION
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    class BattleItemUseCommand: Command{
        override var commandType = CommandType.BATTLEITEMUSE
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    class RepairItemUseCommand: Command{
        override var commandType = CommandType.REPAIRITEMUSE
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

    class MoveItemUseCommand: Command{
        override var commandType = CommandType.MOVEITEMUSE
        override fun execute() {
            TODO("Not yet implemented")
        }
    }

}