package com.apress.spring6recipes.sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleSequenceDaoTest {

  @Test
  void sequences() {
    var dao = new SimpleSequenceDao();
    assertEquals(10000, dao.getNextValue("IT"));
  }

}
