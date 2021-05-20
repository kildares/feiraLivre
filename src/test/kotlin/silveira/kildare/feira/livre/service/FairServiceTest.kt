package silveira.kildare.feira.livre.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import silveira.kildare.feira.livre.dto.FairDto
import silveira.kildare.feira.livre.entity.FairEntity
import silveira.kildare.feira.livre.mother.getFairEntity
import silveira.kildare.feira.livre.mother.getFairList
import silveira.kildare.feira.livre.mother.getValidFairDto
import silveira.kildare.feira.livre.repository.FairRepository
import java.util.*

class FairServiceTest {

    lateinit var fairRepository: FairRepository
    lateinit var fairService: FairService

    @BeforeEach
    fun setUp() {
        fairRepository = mockk()
        fairService = FairService(fairRepository)
    }

    @Test
    fun `should add fair`() {

        val fair = getValidFairDto()

        val expectedEntity = FairEntity(
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
            codSubPref = fair.codSubPref!!.toLowerCase()g
        )

        every { fairRepository.save(expectedEntity) } returns expectedEntity

        val result = fairService.addFair(fair)

        assertTrue(result)

        verify { fairRepository.save(expectedEntity) }
    }

    @Test
    fun `should throw illegalArgumentException when request is invalid`() {

        val validRequest = getValidFairDto()

        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(longitud = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(lat = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(setcens = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(areap = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(codDist = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(distrito = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(codSubPref = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(subPrefe = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(regiao5 = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(regiao8 = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(nomeFeira = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(registro = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(logradouro = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(numero = null)) }
        assertThrows(IllegalArgumentException::class.java) { fairService.addFair(validRequest.copy(bairro = null)) }
    }

    @Test
    fun `should remove fair`() {

        val id = 1L

        every { fairRepository.deleteById(id) } returns Unit

        val result = fairService.removeFair(id)

        assertTrue(result)

        verify { fairRepository.deleteById(id) }
    }

    @Test
    fun `should delete fair`() {

        val id = 1L

        every { fairRepository.deleteById(id) } returns Unit

        val result = fairService.removeFair(id)

        assertTrue(result)

        verify { fairRepository.deleteById(id) }

    }

    @Test
    fun `should update fair`() {

        val id = 1L
        val fairEntity = Optional.of(getFairEntity())
        val fairDto = getFairList()[0].copy(id = "")

        every { fairRepository.findById(id) } returns fairEntity
        every { fairRepository.save(any()) } returns fairEntity.get()

        val result = fairService.updateFair(id, fairDto)

        assertTrue(result)

        verify { fairRepository.findById(any()) }
        verify { fairRepository.save(any()) }
    }

    @Test
    fun `should filter fair list by parameters distrito, regiao5, nomeFeira and bairro and return list`() {

        val distrito = "district"
        val regiao5 = "regiao5"
        val nomeFeira = "nomeFeira"
        val bairro = "bairro"

        val fairs = listOf(
            FairEntity(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira.toLowerCase(), bairro = bairro),
            FairEntity(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira.toLowerCase()),
            FairEntity(distrito = distrito, regiao5 = regiao5, bairro = bairro),
            FairEntity(regiao5 = regiao5, nomeFeira = nomeFeira.toLowerCase(), bairro = bairro),
            FairEntity(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira.toLowerCase(), bairro = bairro),
            FairEntity()
        )

        every { fairRepository.findAll() } returns fairs

        val result =
            fairService.getFairs(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro)

        assertTrue(result.size == 2)
        assertEquals(
            FairDto(
                id = "0",
                distrito = distrito,
                regiao5 = regiao5,
                nomeFeira = nomeFeira.toLowerCase(),
                bairro = bairro
            ), result[0]
        )
        assertEquals(
            FairDto(
                id = "0",
                distrito = distrito,
                regiao5 = regiao5,
                nomeFeira = nomeFeira.toLowerCase(),
                bairro = bairro
            ), result[1]
        )

        verify { fairRepository.findAll() }
    }

    @Test
    fun `should not allow get without filters`(){

        assertThrows(IllegalArgumentException::class.java){fairService.getFairs(null, null, null, null)}

    }

    @Test
    fun `should not allow update to current id`() {

        val dto = FairDto(id = "2")

        assertThrows(IllegalArgumentException::class.java) { fairService.updateFair(1L, dto) }

    }

}