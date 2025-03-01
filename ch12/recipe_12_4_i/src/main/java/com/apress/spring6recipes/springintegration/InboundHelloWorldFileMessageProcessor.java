package com.apress.spring6recipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.io.File;

public class InboundHelloWorldFileMessageProcessor {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @ServiceActivator
  public void handleIncomingFileMessage(Message<File> inbound) {
    var filePayload = inbound.getPayload();
    logger.debug("absolute path: {}, size: {}",
      filePayload.getAbsolutePath(), filePayload.length());
  }
}
