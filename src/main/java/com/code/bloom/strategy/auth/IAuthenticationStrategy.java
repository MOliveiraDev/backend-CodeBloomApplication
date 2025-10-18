package com.code.bloom.strategy.auth;

import com.code.bloom.database.entity.user.UserEntity;

public interface IAuthenticationStrategy {

    UserEntity getAuthenticatedUser();
}
