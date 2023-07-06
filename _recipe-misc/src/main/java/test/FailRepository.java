package test;

import org.springframework.stereotype.Repository;

@Repository
public class FailRepository {

  public void fail() {
    throw new RuntimeException("fail");
  }

}
