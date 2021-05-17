package silveira.kildare.feira.livre.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import silveira.kildare.feira.livre.entity.FairEntity
import silveira.kildare.feira.livre.mother.getFairEntity


@DataJpaTest
class FairRepositoryTest(@Autowired val fairRepository: FairRepository) {

    @Test
    fun `should save fair`(){
        val fair = getFairEntity()
        val result = fairRepository.save(fair)

        assertEquals(fair, result)
    }

}