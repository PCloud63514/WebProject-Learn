package com.pcloud.tinyproductshop.member.dto;

import com.pcloud.tinyproductshop.member.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberDto {
    private Long id;
    @NotEmpty
    private String name;
    private Address address;

    public MemberDto() {
    }

    public MemberDto(Long id, @NotEmpty String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
