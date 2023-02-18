package com.demo.line.service.impl;

import com.demo.line.config.properties.LineProperties;
import com.demo.line.entity.Message;
import com.demo.line.model.PushMessageReqModel;
import com.demo.line.model.UserMessageResModel;
import com.demo.line.repository.MessageRepository;
import com.demo.line.service.LineMessageService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
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

  @Override
  public void pushMessage(PushMessageReqModel pushMessageReqModel) {
    final LineMessagingClient client = LineMessagingClient
        .builder(lineProperties.getChannelToken())
        .build();

    final TextMessage textMessage = new TextMessage(pushMessageReqModel.getText());
    final PushMessage pushMessage = new PushMessage(
        pushMessageReqModel.getUserId(),
        textMessage);

    try {
      client.pushMessage(pushMessage).get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public UserMessageResModel getUserMessages(String userId) {
    List<Message> messages = messageRepository.findByUserId(userId);
    List<MessageContent> messageContents = new ArrayList<>();
    messages.forEach(message -> messageContents.add(message.getMessage()));
    return UserMessageResModel.builder()
        .messages(messageContents)
        .build();
  }

}
