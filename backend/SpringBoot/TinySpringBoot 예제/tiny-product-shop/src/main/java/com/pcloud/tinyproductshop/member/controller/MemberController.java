package com.pcloud.tinyproductshop.member.controller;

import com.pcloud.tinyproductshop.member.domain.Address;
import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.member.service.MemberService;
import com.pcloud.tinyproductshop.member.service.MemberServiceImpl;
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
    public String createMember(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String createMember(MemberForm memberForm, BindingResult result) {
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
    public String memberList(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }
}
