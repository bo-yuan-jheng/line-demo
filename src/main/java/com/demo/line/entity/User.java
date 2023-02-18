package com.demo.line.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: Neil Description: Date: 2023/2/17 6:14 PM
 */

@Data
@SuperBuilder
@NoArgsConstructor
@Document(collection = "user")
public class User {

  @Id
  private String userId;
  @NotBlank
  private String displayName;
  private String pictureUrl;
  private String statusMessage;
  private String language;

}
