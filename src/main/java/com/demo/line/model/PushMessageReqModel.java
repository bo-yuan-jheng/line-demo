package com.demo.line.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Author: Neil Description: Date: 2023/2/18 4:46 PM
 */

@Data
public class PushMessageReqModel {

  @NotBlank
  private String userId;

  @NotBlank
  private String text;

}
