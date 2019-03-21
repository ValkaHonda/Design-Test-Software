package project.config.jsonWebToken;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Lenny on 3/21/2019.
 */
public class JWTToken {
    private String idToken;

    public JWTToken(String idToken) {
        this.idToken = idToken;
    }

    @JsonProperty("id_token")
    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
