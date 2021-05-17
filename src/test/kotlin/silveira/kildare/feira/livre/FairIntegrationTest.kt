package silveira.kildare.feira.livre

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import silveira.kildare.feira.livre.dto.FairDto
import silveira.kildare.feira.livre.mother.getFairList

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class FairIntegrationTest {

    @Autowired
    lateinit var webClient: WebTestClient


    @Test
    fun `should get all fairs`() {

        val response =
            webClient.get().uri("fair").exchange().expectStatus().is2xxSuccessful.expectBodyList(FairDto::class.java)
                .returnResult().responseBody

        assertTrue((response?.size ?: 0) > 0)

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
    fun `should post fair`() {
        val fair = getFairList()[0].copy(id = "")
        webClient.post().uri("fair").bodyValue(fair).header("content-type", "application/json").exchange()
            .expectStatus().is2xxSuccessful.expectBody().isEmpty
    }
}