package com.apress.spring6recipes.court.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PeriodicReservation {

  private String courtName;

  // Accept the time in the SO Date Format {@code yyyy-MM-dd}
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate fromDate;

  // Accept the time in the SO Date Format {@code yyyy-MM-dd}
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate toDate;

  private int period;
  private int hour;
  private Player player;

  public String getCourtName() {
    return courtName;
  }

  public void setCourtName(String courtName) {
    this.courtName = courtName;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public void setToDate(LocalDate toDate) {
    this.toDate = toDate;
  }

}
