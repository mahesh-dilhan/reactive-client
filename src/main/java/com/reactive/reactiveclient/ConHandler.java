package com.reactive.reactiveclient;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ConHandler {

    @Autowired
    private final RSocketRequester rsocketRequester;

    public ConHandler(RSocketRequester rsocketRequester) {
        this.rsocketRequester = rsocketRequester;
    }


    public Mono<ServerResponse>  listCollegaues(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(  rsocketRequester.
                        route("rsock-colleagues").
                        data(Mono.empty())
                        .retrieveFlux(Person.class), Person.class)
                ;
    }


    public Mono<ServerResponse> collegaue(ServerRequest request) {
        var name  =request.pathVariable("name");
        return ok().contentType(APPLICATION_JSON).
                body(
                    rsocketRequester.
                    route("rsock-colleague").
                    data(name)
                    .retrieveMono(Person.class),
                        Person.class)
                .switchIfEmpty(notFound().build());


    }

    public Mono<ServerResponse> saveCollegue(ServerRequest request) {
        var body  =request.bodyToMono(Person.class);
        return ok()
                .contentType(APPLICATION_JSON)
                .body(
                  rsocketRequester.
                  route("rsock-save-colleague")
                  .data(body)
                  .retrieveMono(Person.class)
                ,Person.class);
    }
}
