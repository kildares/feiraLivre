package silveira.kildare.feira.livre.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import silveira.kildare.feira.livre.dto.FairDto
import silveira.kildare.feira.livre.service.FairService
import java.lang.Exception
import java.util.logging.Logger

@RestController
class FairController(@Autowired val fairService: FairService) {

    val logger: Logger = Logger.getLogger("FairControllerLogger")

    @PostMapping("/fair", consumes = ["application/json"])
    fun postFair(@RequestBody fairDto : FairDto) : ResponseEntity<Void> = try {
        fairService.addFair(fairDto)
        ResponseEntity(null, HttpStatus.OK)
    } catch (e : Exception){
        logger.severe("Failure adding resource Fair")
        e.printStackTrace()
        ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @DeleteMapping("/fair/{id}")
    fun removeFair(@PathVariable id : Long) : ResponseEntity<Void> = try {
        fairService.removeFair(id)
        ResponseEntity(null, HttpStatus.OK)
    } catch (e : Exception){
        logger.severe("Failure deleting resource Fair:  ${e.message}")
        ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PutMapping("/fair/{id}", consumes = ["application/json"])
    fun updateFair(@PathVariable id : Long, @RequestBody fair: FairDto) = try {
        val isProcessed = fairService.updateFair(id, fair)
        ResponseEntity(null, if(isProcessed) HttpStatus.OK else HttpStatus.BAD_REQUEST)
    } catch (e : Exception){
        logger.severe("Failure updating resource Fair: ${e.message}")
        ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @GetMapping("/fair")
    fun getFairsByParameters(
        @RequestParam(value = "distrito", required = false)distrito: String?,
        @RequestParam(value = "regiao5", required = false)regiao5: String?,
        @RequestParam(value = "nomeFeira", required = false)nomeFeira: String?,
        @RequestParam(value = "bairro", required = false)bairro: String?,
    ): List<FairDto> {
        return fairService.getFairs(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro)
    }

}