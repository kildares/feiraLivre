package silveira.kildare.feira.livre.service

import org.springframework.stereotype.Component
import silveira.kildare.feira.livre.dto.FairDto
import java.io.File
import java.util.logging.Logger

@Component
class FairCsvService {

    val logger: Logger = Logger.getLogger("FairCsvService")

    fun parseFile(file: File): List<FairDto> {

        val reader = file.bufferedReader()

        reader.readLine() ?: return emptyList()

        var line = reader.readLine()

        val fairDtos = mutableListOf<FairDto>()

        logger.info("Reading lines")

        while (line != null) {
            logger.info("Line: $line")
            val split = line.split(',')

            fairDtos.add(
                FairDto(
                    id = split[0],
                    longitud = split[1],
                    lat = split[2],
                    setcens = split[3],
                    areap = split[4],
                    codDist = split[5],
                    distrito = split[6],
                    codSubPref = split[7],
                    subPrefe = split[8],
                    regiao5 = split[9],
                    regiao8 = split[10],
                    nomeFeira = split[11],
                    registro = split[12],
                    logradouro = split[13],
                    numero = split[14],
                    bairro = split[15],
                    referencia = if (split.size > 16) split[16] else ""
                )
            )
            line = reader.readLine()
        }
        return fairDtos.toList()
    }
}