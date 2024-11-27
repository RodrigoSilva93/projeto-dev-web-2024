package br.edu.utfpr.pb.project.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    APPROVED("Approved"),
    CANCELED("Canceled"),
    PENDING("Pending"),
    REJECTED("Rejected");

    private final String label;
}
