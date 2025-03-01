package com.apress.spring6recipes.calculator;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {

  @DeclareParents(
    value = "com.apress.spring6recipes.calculator.StandardArithmeticCalculator",
    defaultImpl = SimpleMaxCalculator.class)
  public MaxCalculator maxCalculator;

  @DeclareParents(
    value = "com.apress.spring6recipes.calculator.StandardArithmeticCalculator",
    defaultImpl = SimpleMinCalculator.class)
  public MinCalculator minCalculator;

  @DeclareParents(
    value = "com.apress.spring6recipes.calculator.Standard*Calculator",
    defaultImpl = SimpleCounter.class)
  public Counter counter;

  @After("execution(* com.apress.spring6recipes.calculator.*Calculator.*(..)) && this(counter)")
  public void increaseCount(Counter counter) {
    counter.increase();
  }
}
