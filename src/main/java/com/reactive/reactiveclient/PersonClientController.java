package com.reactive.reactiveclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PersonClientController {

    @Autowired
    private final RSocketRequester rsocketRequester;

    public PersonClientController(RSocketRequester rsocketRequester) {
        this.rsocketRequester = rsocketRequester;
    }

    @GetMapping("/listcolleagues")
    public Publisher<Person> listCollegues(){
        return (Publisher<Person>)
                rsocketRequester.
                route("rsock-colleagues").
                        data(Mono.empty()).
                        retrieveFlux(Person.class);
    }

    @GetMapping("/colleague/{name}")
    public Publisher<Person> collegues(@PathVariable("name") String name ){
        return (Publisher<Person>)
                rsocketRequester.
                        route("rsock-colleague").
                        data(name).
                        retrieveMono(Person.class);
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
    private String id;
    private String name;
}