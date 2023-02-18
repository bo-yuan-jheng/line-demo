package com.demo.line.entity;

import com.linecorp.bot.model.event.message.MessageContent;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: Neil Description: Date: 2023/2/17 6:14 PM
 */

@Data
@SuperBuilder
@NoArgsConstructor
@Document(collection = "user_message")
public class Message {

  @Id
  private ObjectId id;

  private String userId;
  @NotBlank
  private MessageContent message;

  private LocalDateTime createTime;


}
