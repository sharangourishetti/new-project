package com.unisonwave.rgt.control.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unisonwave.rgt.control.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}


