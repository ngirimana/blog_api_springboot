package com.springbot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Token Model Information")
public class JwtAuthResponse {
    @Schema(description = "Access token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzY2hhZHJhY2siLCJp")
    private String accessToken;
    @Schema(description = "Token type", example = "Bearer")
    private String tokenType = "Bearer";

}
