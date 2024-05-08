package com.sebin.authservice.oauth2;

import com.sebin.authservice.model.SocialType;

data class OAuthAttributes private constructor(
        val nameAttributeKey: String,
        val oAuth2UserInfo: OAuth2UserInfo
) {
    companion object {
        fun of(socialType: SocialType, userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            return when (socialType) {
                SocialType.KAKAO -> ofKakao(userNameAttributeName, attributes)
                else -> { ofKakao(userNameAttributeName, attributes)
                }
            }
        }

        private fun ofKakao(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            return OAuthAttributes(
                    nameAttributeKey = userNameAttributeName,
                    oAuth2UserInfo = KakaoOAuth2UserInfo(attributes)
            )
        }
    }
}
