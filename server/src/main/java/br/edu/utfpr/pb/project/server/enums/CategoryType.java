package br.edu.utfpr.pb.project.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryType {
    OUTSIDE_VEHICLE("Outside Vehicle"),
    INSIDE_VEHICLE("Inside Vehicle"),
    MISCELLANEOUS("Miscellaneous"),
    MECHANICAL_PARTS("Mechanical Parts"),
    TUNING_PARTS("Tuning Parts");

    private final String label;

}
