package com.example.context_aware_ai_demo.service;

import com.example.context_aware_ai_demo.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;
    private final ChatHistoryService historyService;

    public String chat(String sessionId, String input) {

        List<ChatMessage> history = historyService.getHistory(sessionId);
        historyService.addMessage(sessionId, new ChatMessage("user", input));
        List<org.springframework.ai.chat.messages.Message> messages = new ArrayList<>();
        messages.add(new SystemMessage("You are a helpful assistant. Use conversation history."));
        for (ChatMessage msg : history) {
            if ("user".equals(msg.getRole())) {
                messages.add(new UserMessage(msg.getContent()));
            } else {
                messages.add(new AssistantMessage(msg.getContent()));
            }
        }
        String response = chatClient.prompt()
                .messages(messages)
                .call()
                .content();
        historyService.addMessage(sessionId, new ChatMessage("assistant", response));
        return response;
    }
}
