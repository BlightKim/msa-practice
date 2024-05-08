import com.sebin.authservice.oauth2.OAuth2SuccessHandler
import com.sebin.authservice.service.CustomOAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(private val customOAuth2UserService: CustomOAuth2UserService, private val oAuth2SuccessHandler: OAuth2SuccessHandler) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .cors {
                it.configurationSource(corsConfigurationSource())
            }
            .authorizeHttpRequests {
                it.requestMatchers("/auth/**", "/oauth2/**").permitAll()
            }
            .oauth2Login {
                it.redirectionEndpoint { redirectionEndpointConfig ->
                    redirectionEndpointConfig.baseUri("/oauth2/callback/*")
                }
                it.userInfoEndpoint { userInfoEndpointConfig ->
                    userInfoEndpointConfig.userService(customOAuth2UserService)
                }
                it.successHandler(oAuth2SuccessHandler)
            }

        return http.build()
    }
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()

        config.allowedOriginPatterns = listOf("http://localhost:3000")
        config.allowedHeaders = listOf("*")
        config.allowedMethods = listOf("*")
        config.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }
}