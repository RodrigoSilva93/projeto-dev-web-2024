package br.edu.utfpr.pb.project.server.shared;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse {
    private String message;
}