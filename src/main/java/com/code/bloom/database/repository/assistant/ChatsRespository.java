package com.code.bloom.database.repository.assistant;

import com.code.bloom.database.entity.assistant.ChatsEntity;
import com.code.bloom.database.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatsRespository extends JpaRepository<ChatsEntity, Long> {

    Optional<ChatsEntity> findByUser(UserEntity user);

}
