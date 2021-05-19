package silveira.kildare.feira.livre

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import silveira.kildare.feira.livre.dto.FairResponseDto
import silveira.kildare.feira.livre.mother.getFairList
import silveira.kildare.feira.livre.mother.getValidFairDto

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class FairIntegrationTest {

    @Autowired
    lateinit var webClient: WebTestClient


    @Test
    fun `should get all fairs when regiao5 is leste`() {

        val response1: FairResponseDto? =
            webClient.get().uri("fair?regiao5=leste").exchange().expectStatus().is2xxSuccessful.expectBody(
                FairResponseDto::class.java
            )
                .returnResult().responseBody

        assertTrue(response1?.fairs?.size ?: 0 > 0)

    }

    @Test
    fun `should get all fairs when distrito is iguatemi`() {
        val response2: FairResponseDto? =
            webClient.get().uri("fair?distrito=iguatemi").exchange().expectStatus().is2xxSuccessful.expectBody(
                FairResponseDto::class.java
            )
                .returnResult().responseBody

        assertTrue(response2?.fairs?.size ?: 0 > 0)
    }

    @Test
    fun `should get all fairs when nomeFeira is raposo tavares`() {

        val response3: FairResponseDto? =
            webClient.get().uri("fair?nomeFeira=raposo tavares").exchange().expectStatus().is2xxSuccessful.expectBody(
                FairResponseDto::class.java
            )
                .returnResult().responseBody

        assertTrue(response3?.fairs?.size ?: 0 > 0)


    }

    @Test
    fun `should get all fairs when bairro is lapa`() {

        val response4: FairResponseDto? =
            webClient.get().uri("fair?bairro=lapa").exchange()
                .expectStatus().is2xxSuccessful.expectBody(FairResponseDto::class.java)
                .returnResult().responseBody

        assertTrue(response4?.fairs?.size ?: 0 > 0)

    }

    @Test
    fun `should get now allow get all fairs without filters`() {

        val response: FairResponseDto? =
            webClient.get().uri("fair").exchange()
                .expectStatus().is4xxClientError.expectBody(FairResponseDto::class.java)
                .returnResult().responseBody

        assertEquals(response?.message, "Invalid request parameters")
        assertNull(response?.fairs)

    }


    @Test
    fun `should delete fairs when id is 1`() {
        webClient.delete().uri("fair/1").exchange().expectStatus().is2xxSuccessful.expectBody().isEmpty
    }

    @Test
    fun `should update fair when id is 1`() {
        val fair = getFairList()[0].copy(id = "")
        webClient.put().uri("fair/2").header("content-type", "application/json").bodyValue(fair).exchange()
            .expectStatus().is2xxSuccessful.expectBody().isEmpty
    }

    @Test
    fun `should get bad request when update request is invalid`() {
        val fair = getFairList()[0].copy(setcens = null)
        val result = webClient.put().uri("fair/2").header("content-type", "application/json").bodyValue(fair).exchange()
            .expectStatus().is4xxClientError.expectBody(FairResponseDto::class.java).returnResult().responseBody

        assertEquals(FairResponseDto(message = "Invalid request parameters"), result)
    }

    @Test
    fun `should post fair`() {
        val fair = getValidFairDto()
        val result: FairResponseDto? =
            webClient.post().uri("fair").bodyValue(fair).header("content-type", "application/json").exchange()
                .expectStatus().is2xxSuccessful.expectBody(FairResponseDto::class.java).returnResult().responseBody

        assertEquals(FairResponseDto(message = "Fair added successfully"), result)
    }

    @Test
    fun `should get bad request when post request is invalid`() {
        val fair = getFairList()[0].copy(setcens = null)
        val result = webClient.post().uri("fair").header("content-type", "application/json").bodyValue(fair).exchange()
            .expectStatus().is4xxClientError.expectBody(FairResponseDto::class.java).returnResult().responseBody

        assertEquals(FairResponseDto(message = "Invalid request parameters"), result)
    }


}