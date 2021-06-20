package com.wild.corp.repositories;


import com.wild.corp.model.Etape;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface EtapeRepository extends JpaRepository<Etape, Integer> {

    Etape getOne(Integer etapeId);

    List<Etape> findAll();



}
