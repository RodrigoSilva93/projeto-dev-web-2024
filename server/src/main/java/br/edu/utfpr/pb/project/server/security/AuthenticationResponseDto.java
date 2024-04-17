package br.edu.utfpr.pb.project.server.security;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponseDto {

    private String token;
}
