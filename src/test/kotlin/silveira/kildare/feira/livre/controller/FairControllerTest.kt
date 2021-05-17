package silveira.kildare.feira.livre.controller

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import silveira.kildare.feira.livre.mother.getFairList
import silveira.kildare.feira.livre.service.FairService
import java.lang.RuntimeException
import java.util.logging.Logger

class FairControllerTest {

    lateinit var fairService: FairService
    lateinit var logger: Logger
    lateinit var fairController: FairController

    @BeforeEach
    fun setUp() {
        fairService = mockk()
        fairController = FairController(fairService)
    }

    @Test
    fun `should post fair and return status OK`() {

        val fair = getFairList()[0]

        every { fairService.addFair(fair) } returns true

        val result = fairController.postFair(fair)

        assertEquals(result.statusCode, HttpStatus.OK)

        verify { fairService.addFair(fair) }
    }

    @Test
    fun `should return internal server error when exception is caught`(){


        val fair = getFairList()[0]

        every { fairService.addFair(fair) } throws RuntimeException()

        val result = fairController.postFair(fair)

        assertEquals(result.statusCode, HttpStatus.INTERNAL_SERVER_ERROR)

        verify { fairService.addFair(fair) }
    }

    @Test
    fun `should remove fair`(){

        every { fairService.removeFair(1) } returns true

        val result = fairController.removeFair(1)

        assertEquals(result.statusCode, HttpStatus.OK)

        verify { fairService.removeFair(any()) }
    }

    @Test
    fun `should update fair`(){

        val fair = getFairList()[0]

        every { fairService.updateFair(1, fair) } returns true

        val result = fairController.updateFair(1, fair)

        assertEquals(result.statusCode, HttpStatus.OK)

        verify { fairService.updateFair(any(), any()) }
    }

    @Test
    fun `should return bad request when fairService returns false`(){

        val fair = getFairList()[0]

        every { fairService.updateFair(1, fair) } returns false

        val result = fairController.updateFair(1, fair)

        assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)

        verify { fairService.updateFair(any(), any()) }
    }


    @Test
    fun `should find fairs with parameters district, regiao5, nomeFeira and bairro and return success`(){

        val fairList = getFairList()

        val distrito = "district"
        val regiao5 = "regiao5"
        val nomeFeira = "nomeFeira"
        val bairro = "bairro"

        every { fairService.getFairs(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro) } returns fairList

        val result = fairController.getFairsByParameters(distrito = distrito, regiao5 = regiao5, nomeFeira = nomeFeira, bairro = bairro)

        assertEquals(fairList, result)

        verify { fairService.getFairs(any(), any(), any(), any()) }
    }

}