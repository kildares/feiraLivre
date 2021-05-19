package silveira.kildare.feira.livre.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import silveira.kildare.feira.livre.dto.FairDto
import silveira.kildare.feira.livre.dto.FairResponseDto
import silveira.kildare.feira.livre.service.FairService
import java.util.logging.Logger

@RestController
class FairController(@Autowired val fairService: FairService) {

    val logger: Logger = Logger.getLogger("FairControllerLogger")

    @PostMapping("/fair", consumes = ["application/json"])
    fun postFair(@RequestBody fairDto: FairDto): ResponseEntity<FairResponseDto> = try {
        fairService.addFair(fairDto)
        ResponseEntity(FairResponseDto(message = "Fair added successfully"), HttpStatus.CREATED)
    } catch (e: Exception) {
        logger.severe("Failure adding resource Fair. $e")
        when (e) {
            is IllegalArgumentException -> ResponseEntity(
                FairResponseDto(message = "Invalid request parameters"),
                HttpStatus.BAD_REQUEST
            )
            else -> ResponseEntity(
                FairResponseDto(
                    message = "Please try again later"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }

    @DeleteMapping("/fair/{id}")
    fun removeFair(@PathVariable id: Long): ResponseEntity<Void> = try {
        fairService.removeFair(id)
        ResponseEntity(null, HttpStatus.OK)
    } catch (e: Exception) {
        logger.severe("Failure deleting resource Fair:  ${e.message}")
        ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PutMapping("/fair/{id}", consumes = ["application/json"])
    fun updateFair(@PathVariable id: Long, @RequestBody fair: FairDto) : ResponseEntity<FairResponseDto> = try {
        val isProcessed = fairService.updateFair(id, fair)
        ResponseEntity(null, if (isProcessed) HttpStatus.OK else HttpStatus.BAD_REQUEST)
    } catch (e: Exception) {
        logger.severe("Failure updating resource Fair: ${e.message}")
        when (e) {
            is IllegalArgumentException -> ResponseEntity(
                FairResponseDto(message = "Invalid request parameters"),
                HttpStatus.BAD_REQUEST
            )
            else -> ResponseEntity(
                FairResponseDto(
                    message = "Please try again later"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }

    @GetMapping("/fair")
    fun getFairsByParameters(
        @RequestParam(value = "distrito", required = false) distrito: String?,
        @RequestParam(value = "regiao5", required = false) regiao5: String?,
        @RequestParam(value = "nomeFeira", required = false) nomeFeira: String?,
        @RequestParam(value = "bairro", required = false) bairro: String?,
    ): ResponseEntity<FairResponseDto> = try {
        ResponseEntity(
            FairResponseDto(
                fairs = fairService.getFairs(
                    distrito = distrito,
                    regiao5 = regiao5,
                    nomeFeira = nomeFeira,
                    bairro = bairro
                )
            ), HttpStatus.OK
        )
    } catch (e: Exception) {
        when (e) {
            is IllegalArgumentException -> ResponseEntity(
                FairResponseDto(message = "Invalid request parameters"),
                HttpStatus.BAD_REQUEST
            )
            else -> ResponseEntity(
                FairResponseDto(
                    message = "Please try again later"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }

}