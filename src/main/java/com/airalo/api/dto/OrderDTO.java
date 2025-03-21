package com.airalo.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
public class OrderDTO {
    private final String packageId;
    private final Integer quantity;
    private final String type;
    private final String description;
}
