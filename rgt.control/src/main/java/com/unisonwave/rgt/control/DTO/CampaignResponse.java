package com.unisonwave.rgt.control.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CampaignResponse {

    private String uuid;
    private String publicId;
    private String name;
    private String clientName;
    private String campaignTypeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Integer totalLeads;
    private LocalDateTime createdAt;
}


