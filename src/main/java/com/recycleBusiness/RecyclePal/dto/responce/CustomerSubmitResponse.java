package com.recycleBusiness.RecyclePal.dto.responce;

import com.recycleBusiness.RecyclePal.data.models.Customer;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSubmitResponse {
    private String message;
}
