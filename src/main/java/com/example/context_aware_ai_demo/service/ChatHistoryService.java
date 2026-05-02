package com.example.context_aware_ai_demo.service;

import com.example.context_aware_ai_demo.dto.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatHistoryService {
    private final Map<String, List<ChatMessage>> store = new HashMap<>();

    public List<ChatMessage> getHistory(String sessionId) {
        return store.computeIfAbsent(sessionId, k -> new ArrayList<>());
    }

    public void addMessage(String sessionId, ChatMessage message) {
        getHistory(sessionId).add(message);
    }
}
