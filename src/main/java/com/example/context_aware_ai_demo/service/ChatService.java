package com.example.context_aware_ai_demo.service;

import com.example.context_aware_ai_demo.client.MistralClient;
import com.example.context_aware_ai_demo.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatHistoryService historyService;
    private final MistralClient mistralClient;

    public Mono<String> chat(String sessionId, String userInput) {

        List<ChatMessage> history = historyService.getHistory(sessionId);

        ChatMessage userMessage = new ChatMessage("user", userInput);
        historyService.addMessage(sessionId, userMessage);

        return mistralClient.chat(history)
                .map(aiResponse -> {
                    historyService.addMessage(sessionId,
                            new ChatMessage("assistant", aiResponse));
                    return aiResponse;
                });
    }
}
