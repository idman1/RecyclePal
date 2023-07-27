package com.recycleBusiness.RecyclePal.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.recycleBusiness.RecyclePal.data.models.Address;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WasteCollectionRequestDto {
    private Integer requesterId;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime createdTime;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime pickedUptime;
    private String quantity;
    private Address address;
    private boolean isPicked;
}
