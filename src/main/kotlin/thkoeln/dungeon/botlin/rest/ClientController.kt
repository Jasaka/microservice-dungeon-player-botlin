package thkoeln.dungeon.botlin.rest
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientController {
    @RequestMapping("/")
    fun index(): String {
        return "Index";
    }
}