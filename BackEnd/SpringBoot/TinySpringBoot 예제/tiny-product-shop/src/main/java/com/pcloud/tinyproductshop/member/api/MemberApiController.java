package com.pcloud.tinyproductshop.member.api;

import com.pcloud.tinyproductshop.member.controller.MemberForm;
import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.member.dto.MemberDto;
import com.pcloud.tinyproductshop.member.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("api/members")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("new")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid MemberDto memberdto) {
        Long id = memberService.join(Member.create(memberdto.getName(), memberdto.getAddress()));
        return new CreateMemberResponse(id);
    }

    

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
