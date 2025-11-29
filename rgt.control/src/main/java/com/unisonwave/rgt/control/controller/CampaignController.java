package com.unisonwave.rgt.control.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisonwave.rgt.control.DTO.CampaignCreateRequest;
import com.unisonwave.rgt.control.DTO.CampaignResponse;
import com.unisonwave.rgt.control.service.CampaignService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    public ResponseEntity<CampaignResponse> createCampaign(
             @RequestBody CampaignCreateRequest request
    ) {
        CampaignResponse response = campaignService.createCampaign(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}


