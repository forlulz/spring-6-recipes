package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Member;
import com.apress.spring6recipes.court.domain.Members;
import com.apress.spring6recipes.court.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class RestMemberController {

  private final MemberService memberService;

  public RestMemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public Members getRestMembers() {
    var members = new Members();
    members.addMembers(memberService.findAll());
    return members;
  }

  @GetMapping("/{memberid}")
  public ResponseEntity<Member> getMember(@PathVariable("memberid") long memberID) {
    return memberService.findById(memberID)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Member> newMember(@RequestBody Member newMember) {
    return ResponseEntity.ok(memberService.save(newMember));
  }
}
