package com.sharetreats01.viber_chatbot.support.creator;

import com.sharetreats01.viber_chatbot.infra.viber.dto.request.SendMessageRequest;
import com.sharetreats01.viber_chatbot.infra.viber.dto.request.SendPictureMessageRequest;
import com.sharetreats01.viber_chatbot.infra.viber.service.OrderKeyboardService;
import com.sharetreats01.viber_chatbot.order.dto.response.OrderSuccessResponse;
import com.sharetreats01.viber_chatbot.properties.ChatbotProperties;
import com.sharetreats01.viber_chatbot.util.TrackingDataUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageCreator extends AbstractMessageCreator{
    private final OrderKeyboardService keyboardService;
    private final ChatbotProperties chatbotProperties;
    public OrderMessageCreator(
        TrackingDataUtils trackingDataUtils,
        OrderKeyboardService keyboardService,
        ChatbotProperties chatbotProperties) {
        super(trackingDataUtils);
        this.keyboardService = keyboardService;
        this.chatbotProperties = chatbotProperties;
    }

    @Override
    protected String updateTrackingData(String trackingData) {
        return trackingDataUtils.createTrackingData();
    }

    @Override
    protected SendMessageRequest createMessageRequest(String receiver,
        String trackingData) {
        return null;
    }

   public SendMessageRequest createMessageRequest(
        String receiverId, Integer minApiVer, OrderSuccessResponse orderResponse) {

        SendPictureMessageRequest orderMessage = new SendPictureMessageRequest(
            receiverId,
            chatbotProperties.getBotName(),
            chatbotProperties.getBotAvatar(),
            minApiVer,
            this.updateTrackingData(""),
            orderResponse.getProductUrl(),
            orderResponse.getProductUrl()
        );
        orderMessage.setText(orderResponse.toPictureBodyString());

        String keyboard = keyboardService.orderSuccessMessageKeyboard(orderResponse);
        orderMessage.setKeyboard(keyboard);

        return orderMessage;
    }
}
