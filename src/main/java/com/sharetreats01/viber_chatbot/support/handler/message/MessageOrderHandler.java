package com.sharetreats01.viber_chatbot.support.handler.message;

import com.sharetreats01.viber_chatbot.dto.callback.request.MessageRequest;
import com.sharetreats01.viber_chatbot.dto.callback.request.property.State;
import com.sharetreats01.viber_chatbot.infra.viber.client.ViberWebClient;
import com.sharetreats01.viber_chatbot.infra.viber.dto.request.SendMessageRequest;
import com.sharetreats01.viber_chatbot.order.dto.request.OrderByBotRequest;
import com.sharetreats01.viber_chatbot.order.dto.response.OrderSuccessResponse;
import com.sharetreats01.viber_chatbot.order.service.OrderService;
import com.sharetreats01.viber_chatbot.order.util.OrderTrackingDataUtil;
import com.sharetreats01.viber_chatbot.support.creator.OrderMessageCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageOrderHandler implements MessageHandler{
    private final ViberWebClient webClient;
    private final OrderService orderService;
    private final OrderMessageCreator messageCreator;

    @Override
    public State getMessageHandleType() {
        return State.ORDER;
    }
    @Override
    public void handle(MessageRequest request) {
        String paymentId = request.getMessage().getText();
        String viberId = request.getSender().getId();
        Integer minApi = request.getSender().getApiVersion();

        OrderSuccessResponse orderResponse = getOrderResponse(request, paymentId);
        SendMessageRequest sendMessageRequest =
            messageCreator.createMessageRequest(viberId,minApi,orderResponse);
        webClient.sendMessage(sendMessageRequest);
    }

    private OrderSuccessResponse getOrderResponse (MessageRequest request, String paymentId) {
        String senderId = request.getSender().getId();
        String trackingData = request.getMessage().getTrackingData();

        OrderByBotRequest orderRequest =
            OrderTrackingDataUtil.trackingData2OrderRequest(senderId,trackingData, paymentId);

        OrderSuccessResponse response = orderService.createOrderByBot(orderRequest);

        return response;
    }
}
