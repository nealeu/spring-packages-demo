
[![Run Status](https://api.shippable.com/projects/5c4ad037e77afd07000936d8/badge?branch=master)]()

This is a walking skeleton of a Spring Boot WebFlux application 
that uses second service look up clients.

### To build and run

```bash
git clone https://github.com/nealeu/spring-packages-demo.git
cd spring-packages-demo
./gradlew build
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar --server.port=8080 --spring.security.user.name=some-username --spring.security.user.password=some-password
```

### Command line testing
Then use the following to  
```bash
curl -u some-username:some-password http://localhost:8080/packages
```

If the network is made unavailable, then an error accessing the remote service propagates through as follows: 
```json
{
  "timestamp": "2019-01-26T19:31:01.189+0000",
  "status": 500,
  "error": "Internal Server Error",
  "message": "product-service.herokuapp.com: Temporary failure in name resolution",
  "path": "/packages"
}
```


