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

        if (fair.hasInvalidParameters()) {
            throw IllegalArgumentException()
        }

        logger.info("persisting fairDto: $fair")

        fairRepository.save(
            FairEntity(
                longitud = fair.longitud!!.toLowerCase(),
                lat = fair.lat!!.toLowerCase(),
                setcens = fair.setcens!!.toLowerCase(),
                areap = fair.areap!!.toLowerCase(),
                codDist = fair.codDist!!.toLowerCase(),
                distrito = fair.distrito!!.toLowerCase(),
                referencia = fair.referencia!!.toLowerCase(),
                bairro = fair.bairro!!.toLowerCase(),
                numero = fair.numero!!.toLowerCase(),
                logradouro = fair.logradouro!!.toLowerCase(),
                registro = fair.registro!!.toLowerCase(),
                nomeFeira = fair.nomeFeira!!.toLowerCase(),
                regiao8 = fair.regiao8!!.toLowerCase(),
                regiao5 = fair.regiao5!!.toLowerCase(),
                subPrefe = fair.subPrefe!!.toLowerCase(),
                codSubPref = fair.codSubPref!!.toLowerCase()
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

        if (fair.hasInvalidParameters()) {
            throw IllegalArgumentException()
        }

        logger.info("updating FairId: $id. Content: $fair")

        val fairEntity = fairRepository.findById(id)
        return if (fairEntity.isPresent) {
            fairRepository.save(
                fairEntity.get().copy(
                    longitud = fair.longitud!!,
                    codSubPref = fair.codSubPref!!,
                    subPrefe = fair.subPrefe!!,
                    regiao5 = fair.regiao5!!,
                    regiao8 = fair.regiao8!!,
                    nomeFeira = fair.nomeFeira!!,
                    registro = fair.registro!!,
                    logradouro = fair.logradouro!!,
                    numero = fair.numero!!,
                    bairro = fair.bairro!!,
                    referencia = fair.referencia!!,
                    codDist = fair.codDist!!,
                    areap = fair.areap!!,
                    setcens = fair.setcens!!,
                    distrito = fair.distrito!!,
                    lat = fair.lat!!
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
    ): List<FairDto> =
        when {
            distrito.isNullOrBlank() && regiao5.isNullOrBlank() && nomeFeira.isNullOrBlank() && bairro.isNullOrBlank() -> throw IllegalArgumentException()
            else -> {
                fairRepository.findAll().filter {
                    when {
                        !distrito.isNullOrBlank() &&it.distrito != distrito.toLowerCase() -> false
                        !regiao5.isNullOrBlank() && it.regiao5.toLowerCase() != regiao5.toLowerCase() -> false
                        !nomeFeira.isNullOrBlank() && it.nomeFeira.toLowerCase() != nomeFeira.toLowerCase() -> false
                        !bairro.isNullOrBlank() && it.bairro.toLowerCase() != bairro.toLowerCase() -> false
                        else -> true
                    }
                }
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