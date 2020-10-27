package com.gary.cloudinteractive.webapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Session implements Serializable {
    private String sessionId;
    private String accessToken;
    private String refreshToken;

    public Session(String sessionId, String accessToken, String refreshToken) {
        this.sessionId = sessionId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
