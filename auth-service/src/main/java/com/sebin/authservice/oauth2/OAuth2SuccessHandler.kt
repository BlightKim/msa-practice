package com.sebin.authservice.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import kotlin.jvm.Throws

/**
 * OAuth2 로그인 성공시 토큰 생성
 *
 */
@Component
class OAuth2SuccessHandler : SimpleUrlAuthenticationSuccessHandler() {
    @Value("\${serverRedirectFrontUrl:http://localhost:3000/auth/redirect}")
    private lateinit var serverRedirectFrontUrl: String

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        response.sendRedirect(serverRedirectFrontUrl);
    }
}
