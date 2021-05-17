#OpenFair


###Overview

This is a REST API responsible for consolidating and managing the open street fairs in the city of Sao Paulo.
You are able to perform CRUD operations in the current database and handle the results freely.
Every endpoint handles the same JSON Body
3

###JsonBody example
{ \
"id": "1", \
"longitud" : "-46550164", \
 "lat": "-23558733", \
"setcens": "355030885000091", \
"areap": "3550308005040", \
"codDist":"87", \
"distrito": "VILA FORMOSA", \
"codSubPref": "26", \
"subPrefe": "ARICANDUVA-FORMOSA-CARRAO", \
"regiao5": "Leste", \
"regiao8": "Leste 1", \
"nomeFeira": "VILA FORMOSA", \
"registro": "4041-0", \
"logradouro": "RUA MARAGOJIPE", \
"numero": "S/N", \
"bairro": "VL FORMOSA", \
"referencia": "TV RUA PRETORIA" \
} 

###Endpoints

GET /fair?distrito=DISTRICT_CODE&regiao5=REGION5_CODE&nomeFeira=NOME_FEIRA_CODE&bairro=BAIRRO_NAME \
POST /fair \
PUT /fair/id \
DELETE /fair/id \

