package com.sebin.authservice.oauth2;

abstract class OAuth2UserInfo(
    protected val attributes: Map<String, Any>
) {

    abstract fun getId(): String;  // 소셜 식별 값 : 구글 - "sub", 카카오 - "id", 네이버 - "id"

    abstract fun getNickname(): String?;

    abstract fun getImageUrl(): String?;
}
