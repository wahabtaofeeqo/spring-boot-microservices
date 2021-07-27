/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taocoder.usersservice.repositories;

import com.taocoder.usersservice.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
   
    User findByEmail(String username);
}
