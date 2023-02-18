package com.demo.line.service;

import com.linecorp.bot.model.event.message.MessageContent;

/**
 * Author: Neil Description: Date: 2023/2/18 11:27 AM
 */

public interface LineMessageService {

  void saveUserMessage(String userId, MessageContent messageContent);

}
