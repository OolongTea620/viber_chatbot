package com.sharetreats01.viber_chatbot.repository;

import com.sharetreats01.viber_chatbot.entity.MessageTemplateEntity;
import com.sharetreats01.viber_chatbot.infra.viber.enums.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplateEntity, Integer> {
    Optional<MessageTemplateEntity> findTopByTypeAndLanguageOrderByVersionDesc(MessageType type, String language);
}
