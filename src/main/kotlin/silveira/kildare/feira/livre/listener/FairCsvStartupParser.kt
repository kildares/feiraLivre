package silveira.kildare.feira.livre.listener

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import silveira.kildare.feira.livre.entity.FairEntity
import silveira.kildare.feira.livre.repository.FairRepository
import silveira.kildare.feira.livre.service.FairCsvService
import java.io.File
import java.util.logging.Logger

@Component
class FairCsvStartupParser(
    @Autowired val fairCsvService: FairCsvService,
    @Autowired val fairRepository: FairRepository,
    @Value("\${file.folder}") val folder: String
) : ApplicationListener<ContextRefreshedEvent> {

    private val logger : Logger = Logger.getLogger("FairCsvStartupParser")

    override fun onApplicationEvent(event: ContextRefreshedEvent) {

        fairRepository.saveAll(
            File(folder).listFiles()?.filter { it.extension == "csv" }?.map {
                logger.info("Parsing file ${it.name}")
                fairCsvService.parseFile(it)
            }?.flatten()?.map {
                FairEntity(
                    longitud = it.longitud,
                    lat = it.lat,
                    setcens = it.setcens,
                    areap = it.areap,
                    codDist = it.codDist,
                    distrito = it.distrito,
                    codSubPref = it.codSubPref,
                    subPrefe = it.subPrefe,
                    regiao5 = it.regiao5,
                    regiao8 = it.regiao8,
                    nomeFeira = it.nomeFeira,
                    registro = it.registro,
                    logradouro = it.logradouro,
                    numero = it.numero,
                    bairro = it.bairro,
                    referencia = it.referencia
                )
            } ?: emptyList()
        )
    }
}