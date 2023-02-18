package com.demo.line.service.impl;

import com.demo.line.config.properties.LineProperties;
import com.demo.line.entity.Message;
import com.demo.line.repository.MessageRepository;
import com.demo.line.service.LineMessageService;
import com.linecorp.bot.model.event.message.MessageContent;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Neil Description: Date: 2023/2/18 11:29 AM
 */

@Slf4j
@Service("LineMessageService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LineMessageServiceImpl implements LineMessageService {

  private final MessageRepository messageRepository;
  private final LineProperties lineProperties;

  @Override
  public void saveUserMessage(String userId, MessageContent messageContent) {
    messageRepository.save(
        Message.builder()
            .id(new ObjectId())
            .userId(userId)
            .createTime(LocalDateTime.now())
            .message(messageContent)
            .build()
    );
  }

}
