package com.demo.line.config.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * Author: Neil Description: Date: 2023/2/18 3:28 PM
 */

@Configuration
@ConfigurationProperties(prefix = "line.bot")
@Data
@Validated
public class LineProperties {

  @NotBlank
  private String channelToken;
  @NotBlank
  private String channelSecret;

}
