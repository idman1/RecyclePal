package com.recycleBusiness.RecyclePal.data.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RecyclingCompanines {
    @Id
    @SequenceGenerator(
            name = "RecyclingCenter_sequence",
            sequenceName = "RecyclingCenter_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer centerID;
    private String centerName;
    private String location;
    @OneToOne
    private Address address;

}
