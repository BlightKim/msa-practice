package com.sebin.authservice.oauth2

class KakaoOAuth2UserInfo(attributes: Map<String, Any>): OAuth2UserInfo (attributes) {
    override fun getId(): String {
        return attributes["id"].toString()
    }

    override fun getNickname(): String? {
        val account = attributes["kakao_account"] as? Map<String, Any> ?: return null

        val profile = account["profile"] as? Map<String, Any> ?: return null

        return profile["nickname"] as? String;
    }

    override fun getImageUrl(): String? {
        val account = attributes["kakao_account"] as? Map<String, Any> ?: return null

        val profile = account["profile"] as? Map<String, Any> ?: return null;

        return profile["thumbnail_image_url"] as? String
    }
}