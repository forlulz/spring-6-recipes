package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Player;
import com.apress.spring6recipes.court.domain.Reservation;
import com.apress.spring6recipes.court.domain.SportType;
import com.apress.spring6recipes.court.service.ReservationService;
import com.apress.spring6recipes.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {

  private final ReservationService reservationService;

  public ReservationFormController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @ModelAttribute("sportTypes")
  public List<SportType> populateSportTypes() {
    return reservationService.getAllSportTypes();
  }

  @GetMapping
  public String setupForm(@RequestParam(required = false, value = "username") String username, Model model) {
    var reservation = new Reservation();
    reservation.setPlayer(new Player(username));
    model.addAttribute("reservation", reservation);
    return "reservationForm";
  }

  @PostMapping
  public Callable<String> submitForm(@ModelAttribute("reservation") @Valid Reservation reservation,
                                     BindingResult result, SessionStatus status) {
    return () -> {
      if (result.hasErrors()) {
        return "reservationForm";
      } else {
        // Simulate a slow service call
        Utils.sleep(Duration.ofMillis(ThreadLocalRandom.current().nextInt(1000)));
        reservationService.make(reservation);
        status.setComplete();
        return "redirect:reservationSuccess";
      }
    };
  }
}
