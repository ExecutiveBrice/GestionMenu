package com.wild.corp.repositories;

import com.wild.corp.model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {


    ConfirmationToken findByconfirmationToken(String token);
}