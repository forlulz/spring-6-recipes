package com.apress.spring6recipes.calculator;

import org.aspectj.lang.annotation.Pointcut;

public class CalculatorPointcuts {

  @Pointcut("within(com.apress.spring6recipes.calculator.ArithmeticCalculator+)")
  public void arithmeticOperation() {
  }

  @Pointcut("within(com.apress.spring6recipes.calculator.UnitCalculator+)")
  public void unitOperation() {
  }

  @Pointcut("arithmeticOperation() || unitOperation()")
  public void loggingOperation() {
  }
}
