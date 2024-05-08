package com.sebin.authservice.service

import CustomOAuth2User
import com.sebin.authservice.model.Role
import com.sebin.authservice.model.SocialType
import com.sebin.authservice.oauth2.OAuthAttributes
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CustomOAuth2UserService : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private val log = LoggerFactory.getLogger(CustomOAuth2UserService::class.java)

    @Throws(OAuth2AuthenticationException::class)
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        log.info("CustomOAuth2UserService.loadUser() 실행")
        log.info("userRequest : {}", userRequest)

        val delegate: OAuth2UserService<OAuth2UserRequest, OAuth2User> = DefaultOAuth2UserService()

        val oAuth2User = delegate.loadUser(userRequest)

        val registrationId = userRequest.clientRegistration.registrationId

        val socialType = getSocialType(registrationId)

        val userNameAttributes = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        val attributes = oAuth2User.attributes

        val extractAttributes = OAuthAttributes.of(socialType, userNameAttributes, attributes)


        // DefaultOAuth2User를 구현한 CustomOAuth2User 객체를 생성해서 반환
        return CustomOAuth2User(
            setOf(SimpleGrantedAuthority(Role.ROLE_USER.name)),
            attributes,
            extractAttributes.nameAttributeKey,
            "ksebin96@gmail.com",
            Role.ROLE_USER,
            1L
        )
    }

    private fun getSocialType(registrationId: String): SocialType {
        if (KAKAO == registrationId) {
            return SocialType.KAKAO
        }

        return SocialType.GOOGLE
    }

    companion object {
        private const val KAKAO = "kakao"
    }
}
