package com.wild.corp.repositories;


import com.wild.corp.model.RecetteName;
import com.wild.corp.model.RecetteType;
import com.wild.corp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecetteNameRepository extends JpaRepository<RecetteName, String> {

    RecetteName getOne(String recetteName);

    List<RecetteName> findAll();

    List<RecetteName> findAllByType(RecetteType type);

    @Query(value ="SELECT rn FROM RecetteName rn, Recette r WHERE r.recetteName = rn.nom AND r.user = :user")
    List<RecetteName> findAllByUser(@Param("user") User user);
}

