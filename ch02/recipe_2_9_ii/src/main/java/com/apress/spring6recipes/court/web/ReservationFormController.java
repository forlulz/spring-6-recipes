package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Player;
import com.apress.spring6recipes.court.domain.Reservation;
import com.apress.spring6recipes.court.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {

  private final ReservationService reservationService;

  public ReservationFormController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @GetMapping
  public String setupForm(@RequestParam(required = false, value = "username")
                          String username, Model model) {
    var reservation = new Reservation();
    reservation.setPlayer(new Player(username));
    model.addAttribute("reservation", reservation);
    return "reservationForm";
  }

  @PostMapping
  public String submitForm(@ModelAttribute("reservation") Reservation reservation) {
    reservationService.make(reservation);
    return "redirect:reservationSuccess";
  }
}
