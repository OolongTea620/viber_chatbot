package com.sharetreats01.viber_chatbot.viber.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:properties/viber/viber.properties")
public class ViberProperties {
    @Value("${viber.auth.header}")
    private String viberAuthHeader;

    @Value("${viber.auth.token}")
    private String viberAuthToken;

    @Value("${viber.api.url}")
    private String viberApiUrl;

    @Value("${viber.api.set_webhook.uri}")
    private String setWebhookUri;

    @Value("${viber.api.send_message.uri}")
    private String sendMessageUri;
}
