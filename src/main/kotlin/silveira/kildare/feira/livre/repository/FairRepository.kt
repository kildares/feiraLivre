package silveira.kildare.feira.livre.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import silveira.kildare.feira.livre.entity.FairEntity

@RepositoryRestResource(collectionResourceRel = "fair", path = "fair")
@Repository
interface FairRepository : CrudRepository<FairEntity, Long>