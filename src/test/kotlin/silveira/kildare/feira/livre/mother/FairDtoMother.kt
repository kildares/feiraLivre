package silveira.kildare.feira.livre.mother

import silveira.kildare.feira.livre.dto.FairDto

fun getFairList()= listOf(
    FairDto(
        id = "1",
        longitud = "-46550164",
        lat = "-23558733",
        setcens = "355030885000091",
        areap = "3550308005040",
        codDist = "87",
        distrito = "VILA FORMOSA",
        codSubPref = "26",
        subPrefe = "ARICANDUVA-FORMOSA-CARRAO",
        regiao5 = "Leste",
        regiao8 = "Leste 1",
        nomeFeira = "VILA FORMOSA",
        registro = "4041-0",
        logradouro = "RUA MARAGOJIPE",
        numero = "S/N",
        bairro = "VL FORMOSA",
        referencia = "TV RUA PRETORIA"
    ),
    FairDto(
        id = "878",
        longitud = "-46625252",
        lat = "-23592852",
        setcens = "355030890000053",
        areap = "3550308005046",
        codDist = "92",
        distrito = "VILA MARIANA",
        codSubPref = "12",
        subPrefe = "VILA MARIANA",
        regiao5 = "Sul",
        regiao8 = "Sul 1",
        nomeFeira = "CHACARA KLABIM",
        registro = "3145-3",
        logradouro = "RUA VOLTAIRE C/ PEDRO NICOLE",
        numero = "S/N",
        bairro = "JD VL MARIANA",
        referencia = "AV PREF FABIO PRADO"
    ),
    FairDto(
        id = "879",
        longitud = "-46610849",
        lat = "-23609187",
        setcens = "355030827000078",
        areap = "3550308005044",
        codDist = "27",
        distrito = "CURSINO",
        codSubPref = "13",
        subPrefe = "IPIRANGA",
        regiao5 = "Sul",
        regiao8 = "Sul 1",
        nomeFeira = "CERRACAO",
        registro = "4025-8",
        logradouro = "RUA LINO GUEDES",
        numero = "109.000000",
        bairro = "MOINHO VELHO",
        referencia = "ALTURA DA VERGUEIRO 7450"
    ),
    FairDto(
        id = "880",
        longitud = "-46450426",
        lat = "-23602582",
        setcens = "355030833000022",
        areap = "3550308005274",
        codDist = "32",
        distrito = "IGUATEMI",
        codSubPref = "30",
        subPrefe = "SAO MATEUS",
        regiao5 = "Leste",
        regiao8 = "Leste 2",
        nomeFeira = "JD.BOA ESPERANCA",
        registro = "5171-3",
        logradouro = "RUA IGUPIARA",
        numero = "S/N",
        bairro = "JD BOA ESPERANCA",
        referencia = ""
    )
)


fun getValidFairDto() =  FairDto(
    id = null,
    longitud = "-46550164",
    lat = "-23558733",
    setcens = "355030885000091",
    areap = "3550308005040",
    codDist = "87",
    distrito = "VILA FORMOSA",
    codSubPref = "26",
    subPrefe = "ARICANDUVA-FORMOSA-CARRAO",
    regiao5 = "Leste",
    regiao8 = "Leste 1",
    nomeFeira = "VILA FORMOSA",
    registro = "4041-0",
    logradouro = "RUA MARAGOJIPE",
    numero = "S/N",
    bairro = "VL FORMOSA",
    referencia = "TV RUA PRETORIA"
)