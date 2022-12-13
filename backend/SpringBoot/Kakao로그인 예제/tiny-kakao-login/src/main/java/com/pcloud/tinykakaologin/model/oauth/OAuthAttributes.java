package com.pcloud.tinykakaologin.model.oauth;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String name;
    private String email;
    private String picture;

    public static OAuthAttributes Create(String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .picture((String)kakaoProfile.get("profile_image_url"))
                .email((String) kakaoAccount.get("email"))
                .attributes(kakaoAccount)
                .attributeKey(attributeKey)
                .build();
    }
}
