package com.demo.line.controller;

import com.demo.line.model.PushMessageReqModel;
import com.demo.line.service.LineMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Neil Description: Date: 2023/2/18 4:28 PM
 */

@Slf4j
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserMessageController {

  private final LineMessageService lineMessageService;

  @PostMapping("/push")
  public ResponseEntity<?> pushMessage(@Valid @RequestBody PushMessageReqModel pushMessageReqModel) {
    try {
      lineMessageService.pushMessage(pushMessageReqModel);
      return ResponseEntity.ok().build();
    }  catch (Exception e) {
      log.error("", e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/getMessage/{userId}")
  public ResponseEntity<?> getMessage(@PathVariable String userId) {
    try {
      return ResponseEntity.ok().body(lineMessageService.getUserMessages(userId));
    }  catch (Exception e) {
      log.error("", e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
