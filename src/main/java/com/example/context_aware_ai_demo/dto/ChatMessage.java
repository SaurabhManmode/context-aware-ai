package com.example.context_aware_ai_demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatMessage {
    private String role;   // "user" or "assistant"
    private String content;
}
