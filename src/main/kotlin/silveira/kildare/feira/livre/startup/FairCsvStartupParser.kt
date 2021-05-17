package silveira.kildare.feira.livre.startup

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
                    areap = it.areap.toLowerCase(),
                    codDist = it.codDist.toLowerCase(),
                    distrito = it.distrito.toLowerCase(),
                    codSubPref = it.codSubPref.toLowerCase(),
                    subPrefe = it.subPrefe.toLowerCase(),
                    regiao5 = it.regiao5.toLowerCase(),
                    regiao8 = it.regiao8.toLowerCase(),
                    nomeFeira = it.nomeFeira.toLowerCase(),
                    registro = it.registro.toLowerCase(),
                    logradouro = it.logradouro.toLowerCase(),
                    numero = it.numero.toLowerCase(),
                    bairro = it.bairro.toLowerCase(),
                    referencia = it.referencia.toLowerCase()
                )
            } ?: emptyList()
        )
    }
}