package com.sharetreats01.viber_chatbot.viber.dto;

import com.sharetreats01.viber_chatbot.interaction.dto.callback.parameter.Sender;
import com.sharetreats01.viber_chatbot.viber.enums.MessageType;
import lombok.Getter;

@Getter
public class SendPictureMessageRequest extends SendMessageRequest {
    private final String type = "picture";
    private final String media;
    private final String thumbnail;

    public SendPictureMessageRequest(String receiver, String senderName, String senderAvatar, String trackingData, Integer minApiVersion, String media, String thumbnail) {
        super(receiver, senderName, senderAvatar, trackingData, minApiVersion);
        this.media = media;
        this.thumbnail = thumbnail;
    }
}
