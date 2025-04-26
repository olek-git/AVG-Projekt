package com.example.ecommercesystem.repository;

import com.example.ecommercesystem.entity.Bestellung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestellungRepository extends JpaRepository<Bestellung, Long> {

}