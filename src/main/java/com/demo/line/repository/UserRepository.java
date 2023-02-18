package com.demo.line.repository;

import com.demo.line.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author: Neil Description: Date: 2023/2/18 11:33 AM
 */

public interface UserRepository extends MongoRepository<User, String> {

}
