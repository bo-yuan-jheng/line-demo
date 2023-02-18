package com.demo.line.repository;

import com.demo.line.entity.Message;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author: Neil Description: Date: 2023/2/18 11:33 AM
 */

public interface MessageRepository extends MongoRepository<Message, String> {

  List<Message> findByUserId(String userId);

}
