package com.recycleBusiness.RecyclePal.dto.request;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerRequest {
    private String firstname;
    private String lastname;
    private String address;
    private String phoneNumber;
    private String username;
}
