package com.apress.spring6recipes.reactive.court;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.http.server.HttpServer;

public class ReactorNettyBootstrap {

  public static void main(String[] args) {
    var context = new AnnotationConfigApplicationContext(WebFluxConfiguration.class);
    var handler = WebHttpHandlerBuilder.applicationContext(context).build();

    var adapter = new ReactorHttpHandlerAdapter(handler);

    HttpServer.create().host("localhost").port(8090).handle(adapter).bind().block();
  }

}
