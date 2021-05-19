#OpenFair


###Overview

This is a REST API responsible for consolidating and managing the open street fairs in the city of Sao Paulo.
You are able to perform CRUD operations in the current database and handle the results freely.
It is developed as a Spring Boot application with Kotlin programming language, connected to an in-memory H2 database. Gradle is used for build management.

###Set up
1. The application uses JAVA 11, so make sure you have this JVM version installed.
2. The application loads CSV files containing existing fair data during the boot process. To load the files: create a directory `/csv` in the root of your project and move your files in there.
3. To run the application through Gradle, use the command `gradle bootRun` 
4. Alternatively, you can use the command `gradle bootJar` and generate a .Jar file. The output jar file can be executed with the command `java -jar openFair-0.0.1-SNAPSHOT.jar`

###Logging

An structured logging file is generated and updated during startup until the application is stopped. It is called openFair.log and is
located in the the root of your project.


###Endpoints

####GET /fair
###### Query Params

Returns the list of all fairs that match the given filters. It is not allowed to call without filters.

Allowed filters:
*district*, *bairro*, *region5*, *nomeFeira* and *bairro*

###### HTTP Status Returned

200 - OK
400 - BAD REQUEST
500 - INTERNAL SERVER ERROR

####POST /fair

Receives a JsonBody containing the Fair data to register in the database

###### HTTP Status Returned

201 - CREATED
400 - BAD REQUEST
500 - INTERNAL SERVER ERROR

####PUT /fair/{id}

Receives an ID of an existing fair registered in the database and updates it with the fair data from the JSON body in the request.

###### HTTP Status Returned

200 - OK
400 - BAD REQUEST
500 - INTERNAL SERVER ERROR

####DELETE /fair/id 

Receives an ID of an existing fair registered in the database deletes it. No body is necessary

###### HTTP Status Returned

200 - OK
500 - INTERNAL SERVER ERROR

###JsonBody request example

{
"id": "1",
"longitud": "-46550164",
"lat": "-23558733",
"setcens": "355030885000091",
"areap": "3550308005040",
"codDist": "87",
"distrito": "VILA FORMOSA",
"codSubPref": "26",
"subPrefe": "ARICANDUVA-FORMOSA-CARRAO",
"regiao5": "Leste",
"regiao8": "Leste 1",
"nomeFeira": "BLABLABLA",
"registro": "4041-0",
"logradouro": "RUA MARAGOJIPE",
"numero": "S/N",
"bairro": "VL FORMOSA",
"referencia": "TV RUA PRETORIA"
}



###JsonBody response example

{
"message": null,
"fairs": [
{
"id": "1",
"longitud": "-46550164",
"lat": "-23558733",
"setcens": "355030885000091",
"areap": "3550308005040",
"codDist": "87",
"distrito": "VILA FORMOSA",
"codSubPref": "26",
"subPrefe": "ARICANDUVA-FORMOSA-CARRAO",
"regiao5": "Leste",
"regiao8": "Leste 1",
"nomeFeira": "BLABLABLA",
"registro": "4041-0",
"logradouro": "RUA MARAGOJIPE",
"numero": "S/N",
"bairro": "VL FORMOSA",
"referencia": "TV RUA PRETORIA"
},
{
"id": "4",
"longitud": "-46550164",
"lat": "-23558733",
"setcens": "355030885000091",
"areap": "3550308005040",
"codDist": "87",
"distrito": "VILA FORMOSA",
"codSubPref": "26",
"subPrefe": "ARICANDUVA-FORMOSA-CARRAO",
"regiao5": "Leste",
"regiao8": "Leste 1",
"nomeFeira": "BLABLABLA",
"registro": "4041-0",
"logradouro": "RUA MARAGOJIPE",
"numero": "S/N",
"bairro": "VL FORMOSA",
"referencia": "TV RUA PRETORIA"
}
]
}
