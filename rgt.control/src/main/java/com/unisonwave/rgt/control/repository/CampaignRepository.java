package com.unisonwave.rgt.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisonwave.rgt.control.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, String> {

    // used for duplicate name validation within same client
    boolean existsByClient_IdAndName(Long clientId, String name);

    // to ensure publicId is unique
    boolean existsByPublicId(String publicId);
}

