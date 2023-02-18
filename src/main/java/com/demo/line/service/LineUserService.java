package com.demo.line.service;

import com.demo.line.entity.User;
import com.linecorp.bot.model.profile.UserProfileResponse;

/**
 * Author: Neil Description: Date: 2023/2/18 11:27 AM
 */

public interface LineUserService {

  UserProfileResponse getUserProfile(String userId);
  void saveUserInfo(User user);

}
