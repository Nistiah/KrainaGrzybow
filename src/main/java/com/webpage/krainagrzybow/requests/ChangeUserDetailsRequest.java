package com.webpage.krainagrzybow.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserDetailsRequest {
    private String newUsername;
    private String newEmail;
    private String password;

}
