package com.demo.line.controller;

import com.demo.line.config.properties.LineProperties;
import com.demo.line.handler.LineEventHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.model.event.CallbackRequest;
import com.linecorp.bot.parser.LineSignatureValidator;
import com.linecorp.bot.parser.WebhookParseException;
import jakarta.validation.Valid;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Neil Description: Date: 2023/2/18 11:19 AM
 */

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LineCallbackController {

  private final LineEventHandler lineEventHandler;
  private final LineProperties lineProperties;
  private final ObjectMapper objectMapper;

  @PostMapping("/line/callback")
  public ResponseEntity<?> callback(@Valid @RequestBody String httpRequestBody,
      @RequestHeader(value = "X-Line-Signature") String lineSignature) {
    try {
      String channelSecret = lineProperties.getChannelSecret(); // Channel secret string
      SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(key);
      byte[] source = httpRequestBody.getBytes("UTF-8");
      String signature = Base64.encodeBase64String(mac.doFinal(source));
      final CallbackRequest callbackRequest = objectMapper.readValue(httpRequestBody, CallbackRequest.class);
      if (!signature.equals(lineSignature)) {
        throw new WebhookParseException("Invalid API signature");
      }
      lineEventHandler.eventHandler(callbackRequest);
      return ResponseEntity.ok().build();
    }  catch (Exception e) {
      log.error("", e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
