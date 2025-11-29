package com.unisonwave.rgt.control.DTO;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignCreateRequest {

    private String name;
    private Long clientId;
    private Long campaignTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
  
    private String status;
}


