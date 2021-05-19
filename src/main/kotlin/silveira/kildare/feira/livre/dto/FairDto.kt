package silveira.kildare.feira.livre.dto

data class FairDto(
    val id: String? = "",
    val longitud: String? = "",
    val lat: String? = "",
    val setcens: String? = "",
    val areap: String? = "",
    val codDist: String? = "",
    val distrito: String? = "",
    val codSubPref: String? = "",
    val subPrefe: String? = "",
    val regiao5: String? = "",
    val regiao8: String? = "",
    val nomeFeira: String? = "",
    val registro: String? = "",
    val logradouro: String? = "",
    val numero: String? = "",
    val bairro: String? = "",
    val referencia: String? = ""
){
    fun hasInvalidParameters() = this.longitud.isNullOrBlank() || this.lat.isNullOrBlank() || this.setcens.isNullOrBlank() ||
            this.areap.isNullOrBlank() || this.codDist.isNullOrBlank() || this.distrito.isNullOrBlank() ||
            this.codSubPref.isNullOrBlank() || this.subPrefe.isNullOrBlank() || this.regiao5.isNullOrBlank() ||
            this.regiao8.isNullOrBlank() || this.nomeFeira.isNullOrBlank() || this.registro.isNullOrBlank() ||
            this.logradouro.isNullOrBlank() || this.numero.isNullOrBlank() || this.bairro.isNullOrBlank() || !this.id.isNullOrBlank()
}