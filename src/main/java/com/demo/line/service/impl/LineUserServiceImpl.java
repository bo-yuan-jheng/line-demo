package com.demo.line.service.impl;

import com.demo.line.config.properties.LineProperties;
import com.demo.line.entity.User;
import com.demo.line.repository.UserRepository;
import com.demo.line.service.LineUserService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.profile.UserProfileResponse;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Neil Description: Date: 2023/2/18 3:23 PM
 */

@Slf4j
@Service("LineUserService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LineUserServiceImpl implements LineUserService {

  private final UserRepository userRepository;
  private final LineProperties lineProperties;

  @Override
  public UserProfileResponse getUserProfile(String userId) {
    final LineMessagingClient client = LineMessagingClient
        .builder(lineProperties.getChannelToken())
        .build();

    final UserProfileResponse userProfileResponse;
    try {
      userProfileResponse = client.getProfile(userId).get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      return null;
    }
    return userProfileResponse;
  }

  @Override
  public void saveUserInfo(User user) {
    userRepository.save(user);
  }

}
