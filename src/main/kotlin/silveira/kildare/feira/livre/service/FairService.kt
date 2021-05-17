package silveira.kildare.feira.livre.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import silveira.kildare.feira.livre.dto.FairDto
import silveira.kildare.feira.livre.entity.FairEntity
import silveira.kildare.feira.livre.repository.FairRepository
import java.util.logging.Logger

@Service
class FairService(@Autowired val fairRepository: FairRepository) {

    val logger: Logger = Logger.getLogger("FairServiceLogger")!!

    fun addFair(fair: FairDto): Boolean {

        logger.info("persisting fairDto: $fair")

        fairRepository.save(
            FairEntity(
                longitud = fair.longitud,
                lat = fair.lat,
                setcens = fair.setcens,
                areap = fair.areap,
                codDist = fair.codDist,
                distrito = fair.distrito,
                referencia = fair.referencia,
                bairro = fair.bairro,
                numero = fair.numero,
                logradouro = fair.logradouro,
                registro = fair.registro,
                nomeFeira = fair.nomeFeira,
                regiao8 = fair.regiao8,
                regiao5 = fair.regiao5,
                subPrefe = fair.subPrefe,
                codSubPref = fair.codSubPref
            )
        )
        return true
    }

    fun removeFair(id: Long): Boolean {

        logger.info("removing fairId: $id")


        fairRepository.deleteById(id)
        return true
    }

    fun updateFair(id: Long, fair: FairDto): Boolean {

        if(fair.id.isNotBlank()){
            return false
        }

        logger.info("updating FairId: $id. Content: $fair")

        val fairEntity = fairRepository.findById(id)
        return if (fairEntity.isPresent) {
            fairRepository.save(
                fairEntity.get().copy(
                    longitud = fair.longitud,
                    codSubPref = fair.codSubPref,
                    subPrefe = fair.subPrefe,
                    regiao5 = fair.regiao5,
                    regiao8 = fair.regiao8,
                    nomeFeira = fair.nomeFeira,
                    registro = fair.registro,
                    logradouro = fair.logradouro,
                    numero = fair.numero,
                    bairro = fair.bairro,
                    referencia = fair.referencia,
                    codDist = fair.codDist,
                    areap = fair.areap,
                    setcens = fair.setcens,
                    distrito = fair.distrito,
                    lat = fair.lat
                )
            )
            true
        } else {
            false
        }
    }

    fun getFairs(
        distrito: String?,
        regiao5: String?,
        nomeFeira: String?,
        bairro: String?,
    ): List<FairDto> = fairRepository.findAll().filter {
        when {
            distrito != null && it.distrito != distrito.toLowerCase() -> false
            regiao5 != null && it.regiao5 != regiao5.toLowerCase() -> false
            nomeFeira != null && it.nomeFeira != nomeFeira.toLowerCase() -> false
            bairro != null && it.bairro != bairro.toLowerCase() -> false
            else -> true
        }
    }.map {
        FairDto(
            distrito = it.distrito,
            longitud = it.longitud,
            lat = it.lat,
            setcens = it.setcens,
            areap = it.areap,
            codDist = it.codDist,
            codSubPref = it.codSubPref,
            subPrefe = it.subPrefe,
            regiao5 = it.regiao5,
            regiao8 = it.regiao8,
            nomeFeira = it.nomeFeira,
            registro = it.registro,
            logradouro = it.logradouro,
            numero = it.numero,
            bairro = it.bairro,
            referencia = it.referencia,
            id = it.id.toString()
            )
    }

}