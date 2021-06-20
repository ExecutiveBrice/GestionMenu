package com.wild.corp.repositories;


import com.wild.corp.model.Recette;
import com.wild.corp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecetteRepository extends JpaRepository<Recette, Integer> {

    Recette getOne(Integer recetteId);

    List<Recette> findAll();

    List<Recette> findAllByUser(User user);

    //@Query(value ="SELECT r FROM Recette r WHERE :user in r.stars")
    //@Query(value ="SELECT r FROM recette r, recette_app_user ru WHERE ru.recette_id = r.id and :userId = ru.stars_id",nativeQuery = true)
    //List<Recette> findAllByStarr(@Param("userId") Integer userId);

    List<Recette> findByStars(User user);


    @Query(value ="SELECT r FROM Recette r WHERE r.recetteName.type.name = :type")
    List<Recette> findAllByType(@Param("type") String type);

    @Query(value ="SELECT r FROM Recette r WHERE r.recetteName.nom = :recetteName")
    List<Recette> findAllByRecetteName(@Param("recetteName") String recetteName);

}
