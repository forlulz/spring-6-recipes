package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Player;
import com.apress.spring6recipes.court.domain.Reservation;
import com.apress.spring6recipes.court.domain.ReservationValidator;
import com.apress.spring6recipes.court.domain.SportType;
import com.apress.spring6recipes.court.service.ReservationNotAvailableException;
import com.apress.spring6recipes.court.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {

  private final ReservationService reservationService;
  private final ReservationValidator validator;

  public ReservationFormController(ReservationService reservationService, ReservationValidator validator) {
    this.reservationService = reservationService;
    this.validator = validator;
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
  public String submitForm(@ModelAttribute("reservation") Reservation reservation, BindingResult result,
                           SessionStatus status) {
    validator.validate(reservation, result);
    if (result.hasErrors()) {
      return "reservationForm";
    } else {
      reservationService.make(reservation);
      status.setComplete();
      return "redirect:reservationSuccess";
    }
  }

  @ExceptionHandler(ReservationNotAvailableException.class)
  public String handle(ReservationNotAvailableException ex) {
    return "reservationNotAvailable";
  }

  @ExceptionHandler
  public String handleDefault(Exception e) {
    return "error";
  }
}
