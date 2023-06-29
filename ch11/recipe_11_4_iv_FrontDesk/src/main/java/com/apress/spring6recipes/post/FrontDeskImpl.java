package com.apress.spring6recipes.post;

import org.springframework.jms.core.support.JmsGatewaySupport;

import java.util.HashMap;
import java.util.Map;

public class FrontDeskImpl extends JmsGatewaySupport implements FrontDesk {

  public void sendMail(final Mail mail) {
    Map<String, Object> map = new HashMap<>();
    map.put("mailId", mail.getMailId());
    map.put("country", mail.getCountry());
    map.put("weight", mail.getWeight());
    getJmsTemplate().convertAndSend(map);
  }
}
