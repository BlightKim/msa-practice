import com.sebin.authservice.model.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.DefaultOAuth2User

class CustomOAuth2User(
    authorities: Collection<GrantedAuthority>,
    attributes: Map<String, Any>,
    nameAttributeKey: String,
    private val email: String,
    private val role: Role,
    private val userId: Long
) : DefaultOAuth2User(authorities, attributes, nameAttributeKey) {
    // CustomOAuth2User를 구현하는 이유는, Resource Server(카카오 이런곳)에서 제공하지 않는 추가 정보들을 내 서비스에서 가지고 있기 위함임.
    // 따라서 Resource Server에서 제공하는 정보만 사용해도 되는 프로젝트라면, 굳이 CustomOAuth2User를 구현하지 않고, 일반 DefalutOAuth2User를 사용하면 된다.
}