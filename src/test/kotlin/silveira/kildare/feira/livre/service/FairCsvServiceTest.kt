package silveira.kildare.feira.livre.service

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.event.ContextRefreshedEvent
import silveira.kildare.feira.livre.mother.getFairList
import java.io.File

class FairCsvServiceTest {

    private lateinit var fairCsvService: FairCsvService

    @BeforeEach
    fun setUp() {
        fairCsvService = FairCsvService()
    }

    @Test
    fun `should parse csv file DEINFO_AB_FEIRASLIVRES_2014`() {

        val fileName = "csvs/UNIT_TEST_FILE.csv";

        val file = File(javaClass.classLoader.getResource(fileName)!!.file)

        val result = fairCsvService.parseFile(file)


        val mocks = getFairList()

        assertEquals(4, result.size)
        assertEquals(result[0], mocks[0])
        assertEquals(result[1], mocks[1])
        assertEquals(result[2], mocks[2])
        assertEquals(result[3], mocks[3])
    }

}