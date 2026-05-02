package com.example.context_aware_ai_demo.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private String sessionId;
    private String input;
}
