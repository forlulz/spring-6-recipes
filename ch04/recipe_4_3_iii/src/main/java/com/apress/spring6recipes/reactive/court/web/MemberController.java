package com.apress.spring6recipes.reactive.court.web;

import com.apress.spring6recipes.reactive.court.domain.Member;
import com.apress.spring6recipes.reactive.court.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public Flux<Member> list() {
    return memberService.findAll();
  }

  @PostMapping
  public Mono<Member> create(@Valid @RequestBody Member member) {
    return memberService.save(member);
  }
}
