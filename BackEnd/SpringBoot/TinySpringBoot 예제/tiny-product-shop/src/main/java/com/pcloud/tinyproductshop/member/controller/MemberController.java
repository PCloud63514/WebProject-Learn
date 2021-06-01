package com.pcloud.tinyproductshop.member.controller;

import com.pcloud.tinyproductshop.member.domain.Address;
import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String CreateMember(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String CreateMember(MemberForm memberForm, BindingResult result) {
        if(result.hasErrors()) {
            return "members/createMemberForm";
        }
        Member member = new Member();
        member.setName(memberForm.name);
        member.setAddress(new Address(memberForm.city, memberForm.street, memberForm.zipcode));
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping
    public String MemberList(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }
}
