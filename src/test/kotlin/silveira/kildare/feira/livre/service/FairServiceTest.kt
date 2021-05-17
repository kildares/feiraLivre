package silveira.kildare.feira.livre.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import silveira.kildare.feira.livre.dto.FairDto
import silveira.kildare.feira.livre.entity.FairEntity
import silveira.kildare.feira.livre.mother.getFairEntity
import silveira.kildare.feira.livre.mother.getFairList
import silveira.kildare.feira.livre.repository.FairRepository
import java.util.*

class FairServiceTest {

    lateinit var fairRepository : FairRepository
    lateinit var fairService : FairService

    @BeforeEach
    fun setUp(){
        fairRepository = mockk()
        fairService = FairService(fairRepository)
    }

    @Test
    fun `should add fair`(){

        val fair = getFairList()[0]

        val expectedEntity = FairEntity(
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
            codSubPref = fair.codSubPref,
            id = fair.id.toLong()
        )

        every{ fairRepository.save(expectedEntity)} returns expectedEntity

        val result = fairService.addFair(fair)

        assertTrue(result)

        verify{ fairRepository.save(expectedEntity)}
    }

    @Test
    fun `should remove fair`(){

        val id = 1L

        every{fairRepository.deleteById(id)} returns Unit

        val result = fairService.removeFair(id)

        assertTrue(result)

        verify { fairRepository.deleteById(id)}
    }

    @Test
    fun `should delete fair`(){

        val id = 1L

        every{fairRepository.deleteById(id)} returns Unit

        val result = fairService.removeFair(id)

        assertTrue(result)

        verify { fairRepository.deleteById(id)}

    }

    @Test
    fun `should update fair`(){

        val id = 1L
        val fairEntity = Optional.of(getFairEntity())
        val fairDto = getFairList()[0]

        every{fairRepository.findById(id)} returns fairEntity
        every { fairRepository.save(any())} returns fairEntity.get()

        val result = fairService.updateFair(id, fairDto)

        assertTrue(result)

        verify { fairRepository.findById(any())}
        verify { fairRepository.save(any())}
    }

    @Test
    fun `should filter fair list by parameters distrito, regiao5, nomeFeira and bairro and return list`(){

        val distrito = "district"
        val regiao5 = "regiao5"
        val nomeFeira = "nomeFeira"
        val bairro = "bairro"

        val fairs = listOf(
            FairEntity(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro),
            FairEntity(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira),
            FairEntity(distrito = distrito, regiao5 = regiao5, bairro = bairro),
            FairEntity(regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro),
            FairEntity(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro),
            FairEntity()
            )

        every { fairRepository.findAll() } returns fairs

        val result = fairService.getFairs(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro)

        assertTrue(result.size == 2)
        assertEquals(FairDto(id ="0", distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro), result[0])
        assertEquals(FairDto(id ="0", distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro), result[1])

        verify { fairRepository.findAll()}
    }

}