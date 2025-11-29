package com.unisonwave.rgt.control.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unisonwave.rgt.control.DTO.CampaignCreateRequest;
import com.unisonwave.rgt.control.DTO.CampaignResponse;
import com.unisonwave.rgt.control.entity.Campaign;
import com.unisonwave.rgt.control.entity.CampaignType;
import com.unisonwave.rgt.control.entity.Client;
import com.unisonwave.rgt.control.exception.DuplicateCampaignException;
import com.unisonwave.rgt.control.repository.CampaignRepository;
import com.unisonwave.rgt.control.repository.CampaignTypeRepository;
import com.unisonwave.rgt.control.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ClientRepository clientRepository;
    private final CampaignTypeRepository campaignTypeRepository;

    private final Random random = new Random();

    @Transactional
    public CampaignResponse createCampaign(CampaignCreateRequest request) {

        // 1. Validate start/end dates
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        // 2. Check duplicate name for same client
        boolean exists = campaignRepository
                .existsByClient_IdAndName(request.getClientId(), request.getName());
        if (exists) {
            throw new DuplicateCampaignException(
                "Campaign '" + request.getName() +
                "' already exists for this client (ID: " + request.getClientId() + ")"
            );
        }

        // 3. Load Client (FK)
        Client client = (Client) clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        // 4. Load CampaignType (FK)
        CampaignType campaignType = campaignTypeRepository.findById(request.getCampaignTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Campaign type not found"));

        // 5. Create Campaign entity
        Campaign campaign = new Campaign();
        campaign.setUuid(UUID.randomUUID().toString());
        campaign.setPublicId(generatePublicId());
        campaign.setName(request.getName());
        campaign.setClient((com.unisonwave.rgt.control.entity.Client) client);
        campaign.setCampaignType(campaignType);
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setStatus(
                request.getStatus() != null ? request.getStatus() : "Active"
        );
        campaign.setTotalLeads(0);

        Campaign saved = campaignRepository.save(campaign);

        // 6. Map entity → response
        CampaignResponse response = new CampaignResponse();
        response.setUuid(saved.getUuid());
        response.setPublicId(saved.getPublicId());
        response.setName(saved.getName());
        response.setClientName(saved.getClient().getName());
        response.setCampaignTypeName(saved.getCampaignType().getName());
        response.setStartDate(saved.getStartDate());
        response.setEndDate(saved.getEndDate());
        response.setStatus(saved.getStatus());
        response.setTotalLeads(saved.getTotalLeads());
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }

    private String generatePublicId() {
        String id;
        do {
            int num = 100000 + random.nextInt(900000);
            id = "CPG-" + num;
        } while (campaignRepository.existsByPublicId(id));
        return id;
    }
}


