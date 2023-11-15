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
@Schema( description = "Login Model Information")
public class LoginDto {
    @Schema(description = "Username or email of the user", example = "ngirimana")
    private String usernameOrEmail;
    @Schema(description = "Password of the user", example = "123456")
    private String password;
}
