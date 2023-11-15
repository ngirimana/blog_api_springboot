package com.springbot.blog.payload;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema( description = "User Model Information")
public class RegisterDto {
    @Schema(description = "Name of the user", example = "Schadrack")
    private String name;
    @Schema(description = "Username of the user", example = "ngirimana")
    private String username;
    @Schema(description = "Email of the user", example = "ngirimana@gmail.com")
    private String email;
    @Schema(description = "Password of the user", example = "123456")
    private String password;
}
