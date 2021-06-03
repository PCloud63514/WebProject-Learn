package com.pcloud.tinyproductshop.member.api;

import com.pcloud.tinyproductshop.member.domain.Address;
import com.pcloud.tinyproductshop.member.domain.Member;
import com.pcloud.tinyproductshop.member.dto.MemberDto;
import com.pcloud.tinyproductshop.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(("api/members"))
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/new")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid MemberDto memberdto) {
        Long id = memberService.join(Member.create(memberdto.getName(), memberdto.getAddress()));
        return new CreateMemberResponse(id);
    }

    @GetMapping("/{id}")
    public MemberDto memberOne(@PathVariable Long id) {
        System.out.println(id);
        Member member = memberService.findOne(id).get();
        MemberDto memberDto = new MemberDto(member.getId(), member.getName(), member.getAddress());

        return memberDto;
    }

    @GetMapping("/list")
    public Result memberList() {
        List<Member> members = memberService.findMembers();
        List<MemberDto> collect = members.stream()
                .map(m -> new MemberDto(m.getId(), m.getName(), m.getAddress()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @PutMapping("/{id}")
    public UpdateMemberResponse UpdateMemberResponse(
            @PathVariable Long id,
            @RequestBody @Valid MemberDto memberDto) {

        memberService.update(id, memberDto.getName());
        Member member = memberService.findOne(id).get();

        UpdateMemberResponse updateMemberResponse = new UpdateMemberResponse();
        updateMemberResponse.setId(member.getId());
        updateMemberResponse.setName(member.getName());
        updateMemberResponse.setAddress(member.getAddress());

        return updateMemberResponse;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    static class UpdateMemberResponse {
        private Long id;
        private String name;
        private Address address;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
