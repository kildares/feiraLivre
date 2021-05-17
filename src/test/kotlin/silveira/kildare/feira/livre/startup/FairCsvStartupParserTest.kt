package silveira.kildare.feira.livre.startup

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.event.ContextRefreshedEvent
import silveira.kildare.feira.livre.entity.FairEntity
import silveira.kildare.feira.livre.mother.getFairList
import silveira.kildare.feira.livre.repository.FairRepository
import silveira.kildare.feira.livre.service.FairCsvService

class FairCsvStartupParserTest {

    lateinit var fairCsvService : FairCsvService
    lateinit var fairRepository : FairRepository
    lateinit var fairCsvStartupParser : FairCsvStartupParser

    @BeforeEach
    fun setUp(){
        fairCsvService = mockk()
        fairRepository = mockk()
        val directory = javaClass.classLoader.getResource("csvs")!!.file
        fairCsvStartupParser = FairCsvStartupParser(fairCsvService, fairRepository, directory)
    }

    @Test
    fun `should load files in folder`(){

        val event = mockk<ContextRefreshedEvent>()

        val slot = slot<Iterable<FairEntity>>()

        every { fairCsvService.parseFile(any()) } returns getFairList()

        every { fairRepository.saveAll(capture(slot)) } returns mockk()

        fairCsvStartupParser.onApplicationEvent(event)

        assertTrue((slot.captured as List).size > 0)

        verify { fairCsvService.parseFile(any())}

    }
}