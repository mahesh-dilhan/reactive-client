# Prerequisites 
reactive-server should be setup in order to client up and run


# Reactive examples
One word .i.e. Non-Blocking

To verify we use `BlockHond.install`

Examples in this repo:

 * Router Functions
 * WebFlux examples with Mongo DB
 * RSocket example with Mongo DB

## Setup & build

OpenJDK 13 (let jenv to manage multiple JDKs )

install `httpie`
```
https://httpie.org/
``` 

Download `RSocket` Client library
```
wget -O rsc.jar https://github.com/making/rsc/releases/download/0.4.2/rsc-0.4.2.jar
```

Run `mvn package` to build a single executable JAR file.
 
Server starts : `Netty` (8000)  

```
2020-06-29 21:22:52.369  INFO 43684 --- [           main] c.r.r.ReactiveClientApplication          : Starting ReactiveClientApplication on Maheshs-MBP with PID 43684 (/Users/mahesh/play/reactive/reactive-client/target/classes started by mahesh in /Users/mahesh/play/reactive/reactive-client)
2020-06-29 21:22:52.371  INFO 43684 --- [           main] c.r.r.ReactiveClientApplication          : No active profile set, falling back to default profiles: default
2020-06-29 21:22:53.878  INFO 43684 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port(s): 8000
2020-06-29 21:22:53.887  INFO 43684 --- [           main] c.r.r.ReactiveClientApplication          : Started ReactiveClientApplication in 1.891 seconds (JVM running for 2.239)
```
 
### Flux Endpoint
Lets invoke `/listcolleagues` endpoint

```
Maheshs-MBP:reactive-client mahesh$ http :8000/listcolleagues
HTTP/1.1 200 OK
Content-Type: application/json
transfer-encoding: chunked

[
    {
        "id": "5ef9eb25dba5750044585963",
        "name": "Xiaoyuan"
    },
    {
        "id": "5ef9eb25dba5750044585964",
        "name": "Lloyd"
    },
    {
        "id": "5ef9eb25dba5750044585965",
        "name": "Bing Jie"
    },
    {
        "id": "5ef9eb25dba5750044585966",
        "name": "Swapnik"
    },
    {
        "id": "5ef9eb25dba5750044585967",
        "name": "Reni"
    },
    {
        "id": "5ef9eb25dba5750044585969",
        "name": "Murali"
    },
    {
        "id": "5ef9eb25dba575004458596a",
        "name": "Naren"
    },
    {
        "id": "5ef9eb25dba575004458596c",
        "name": "SriDevi"
    },
    {
        "id": "5ef9eb25dba5750044585968",
        "name": "Vlad"
    },
    {
        "id": "5ef9eb25dba575004458596b",
        "name": "Kishore"
    }
]


```
Lets invoke `/colleague/{name}` endpoint

```
Maheshs-MBP:react-server mahesh$ http :8000/colleague/Reni
HTTP/1.1 200 OK
Content-Length: 47
Content-Type: application/json

{
    "id": "5ef9eb25dba5750044585967",
    "name": "Reni"
}

```

Lets invoke `/colleague/<name>` endpoint
```
Maheshs-MBP:react-server mahesh$ http :8080/colleague/Suresh
HTTP/1.1 200 OK
Content-Length: 49
Content-Type: application/json

{
    "id": "5ef8aca638737c328db613f3",
    "name": "Suresh"
}
```

## Flux Endpoint POST
There different ways to consume POST. I would suggest to stick with Spring documentation. 
use ServerRequest

Lets invoke `/colleage` with data `-data '{"name":"Jitesh"}'`
 
```
Maheshs-MBP:react-server mahesh$ curl localhost:8000/colleague -H "content-type: application/json" -d '{"name":"Jitesh"}'
```


### Query Endpoint
```
HTTP/1.1 200 OK
Content-Type: application/json
transfer-encoding: chunked

[
    {
        "id": "5ef9eb25dba5750044585963",
        "name": "Xiaoyuan"
    },
    {
        "id": "5ef9eb25dba5750044585964",
        "name": "Lloyd"
    },
    {
        "id": "5ef9eb25dba5750044585965",
        "name": "Bing Jie"
    },
    {
        "id": "5ef9eb25dba5750044585966",
        "name": "Swapnik"
    },
    {
        "id": "5ef9eb25dba5750044585967",
        "name": "Reni"
    },
    {
        "id": "5ef9eb25dba5750044585969",
        "name": "Murali"
    },
    {
        "id": "5ef9eb25dba575004458596a",
        "name": "Naren"
    },
    {
        "id": "5ef9eb25dba575004458596c",
        "name": "SriDevi"
    },
    {
        "id": "5ef9eb25dba5750044585968",
        "name": "Vlad"
    },
    {
        "id": "5ef9eb25dba575004458596b",
        "name": "Kishore"
    },
    {
        "id": "5ef9f97cdba575004458596d",
        "name": "Jitesh"
    }
]

```


### TODO
Error Handling
Event Handling