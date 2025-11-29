package com.unisonwave.rgt.control.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "campaigns",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"client_id", "name"})  // same client + same name not allowed
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    @Id
    @Column(length = 36)
    private String uuid; // we set in service via UUID.randomUUID().toString()

    @Column(name = "public_id", nullable = false, unique = true, length = 20)
    private String publicId;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;  // FK -> clients.id

    @ManyToOne
    @JoinColumn(name = "campaign_type_id", nullable = false)
    private CampaignType campaignType;  // FK -> campaign_type.id

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, length = 20)
    private String status;  // Active, Paused, Draft

    @Column(name = "total_leads")
    private Integer totalLeads = 0;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}


