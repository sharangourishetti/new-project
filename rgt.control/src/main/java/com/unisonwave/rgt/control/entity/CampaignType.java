package com.unisonwave.rgt.control.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "campaign_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // BIGINT AUTO_INCREMENT
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String name;
}
