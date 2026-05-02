package com.example.context_aware_ai_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(MistralAiChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }
}
