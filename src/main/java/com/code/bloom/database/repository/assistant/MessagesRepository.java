package com.code.bloom.database.repository.assistant;

import com.code.bloom.database.entity.assistant.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<MessagesEntity, Long> {
}
