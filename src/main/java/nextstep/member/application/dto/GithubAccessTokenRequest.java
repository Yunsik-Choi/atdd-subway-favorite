package nextstep.member.application.dto;

public class GithubAccessTokenRequest {

    private String code;
    private String clientId;
    private String clientSecret;

    public GithubAccessTokenRequest() {
    }

    public GithubAccessTokenRequest(final String code, final String clientId, final String clientSecret) {
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getCode() {
        return code;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
