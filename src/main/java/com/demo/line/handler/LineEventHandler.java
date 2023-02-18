package com.demo.line.handler;

import com.demo.line.entity.User;
import com.demo.line.service.LineMessageService;
import com.demo.line.service.LineUserService;
import com.linecorp.bot.model.event.CallbackRequest;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.profile.UserProfileResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: Neil Description: Date: 2023/2/18 3:50 PM
 */

@Slf4j
@Service("LineEventHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LineEventHandler {
  private final LineUserService lineUserService;
  private final LineMessageService lineMessageService;

  @Transactional
  public void eventHandler(CallbackRequest callbackRequest) {
    for (Event event : callbackRequest.getEvents()) {
      log.info("saveUserMessage callbackRequest : {}", callbackRequest);

      if (event instanceof MessageEvent<?>) {
        MessageContent messageContent = ((MessageEvent<?>) event).getMessage();
        lineMessageService.saveUserMessage(event.getSource().getUserId(), messageContent);
      }
      if (event instanceof FollowEvent) {
        UserProfileResponse userProfile = lineUserService.getUserProfile(event.getSource().getUserId());
        lineUserService.saveUserInfo(User.builder()
            .userId(userProfile.getUserId())
            .displayName(userProfile.getDisplayName())
            .statusMessage(Optional.ofNullable(userProfile.getStatusMessage()).isPresent() ? userProfile.getStatusMessage() : null)
            .language(Optional.ofNullable(userProfile.getLanguage()).isPresent() ? userProfile.getLanguage() : null)
            .pictureUrl(Optional.ofNullable(userProfile.getPictureUrl()).isPresent() ? userProfile.getPictureUrl().toString() : null)
            .build()
        );
      }
    }
  }

}
