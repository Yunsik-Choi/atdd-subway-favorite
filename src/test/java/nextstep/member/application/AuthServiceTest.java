package nextstep.member.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.member.application.dto.TokenRequest;
import nextstep.member.application.dto.TokenResponse;
import nextstep.member.domain.Member;
import nextstep.member.domain.MemberRepository;
import nextstep.member.domain.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("인증 관련 기능")
class AuthServiceTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private Member member;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();

        this.member = new Member(
                "email.google.com", "1234", 26,
                List.of(RoleType.ROLE_MEMBER.name(), RoleType.ROLE_ADMIN.name())
        );
    }

    @DisplayName("토큰을 발급한다.")
    @Test
    void createToken() {
        LocalDateTime localDateTime = LocalDateTime.now();
        memberRepository.save(member);
        String expected = jwtTokenProvider.createToken(member, localDateTime);
        TokenRequest token = new TokenRequest(member.getEmail(), member.getPassword());

        TokenResponse tokenResponse = authService.createTokenFrom(token, localDateTime);

        assertThat(tokenResponse.getAccessToken()).isEqualTo(expected);
    }
}
