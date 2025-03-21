package com.airalo.api.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:system.properties")
public interface Configuration extends Config {

    @Key("uri.base")
    String baseUri();

    @Key("uri.sims")
    String simsUri();

    @Key("uri.orders")
    String ordersUri();

    @Key("uri.token")
    String tokenUri();


    @Key("content.type")
    String contentType();

    @Key("port")
    int httpPort();

    @Key("client.id")
    String clientId();

    @Key("client.secret")
    String clientSecret();

}
