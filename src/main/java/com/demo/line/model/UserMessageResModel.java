package com.demo.line.model;

import com.linecorp.bot.model.event.message.MessageContent;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Author: Neil Description: Date: 2023/2/17 6:17 PM
 */

@Data
@Builder
public class UserMessageResModel {

  @NotBlank
  private List<MessageContent> messages;

}
