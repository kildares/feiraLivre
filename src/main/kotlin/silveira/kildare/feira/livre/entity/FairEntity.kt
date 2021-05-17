package silveira.kildare.feira.livre.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class FairEntity(
    @Id
    @GeneratedValue
    val id: Long = 0L,
    val longitud: String = "",
    val lat: String = "",
    val setcens: String = "",
    val areap: String = "",
    val codDist: String = "",
    val distrito: String = "",
    val codSubPref: String = "",
    val subPrefe: String = "",
    val regiao5: String = "",
    val regiao8: String = "",
    val nomeFeira: String = "",
    val registro: String = "",
    val logradouro: String = "",
    val numero: String = "",
    val bairro: String = "",
    val referencia: String = ""
)