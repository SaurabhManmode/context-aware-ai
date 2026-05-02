package com.example.context_aware_ai_demo.client;

import com.example.context_aware_ai_demo.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
@RequiredArgsConstructor
public class MistralClient {
    private final WebClient webClient = WebClient.create();

    @Value("${mistral.url}")
    private String url;

    @Value("${mistral.api-key}")
    private String apiKey;

    @Value("${mistral.model}")
    private String model;

    public Mono<String> chat(List<ChatMessage> messages) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", model);
        payload.put("messages", messages);
        payload.put("max_tokens", 1000);
        payload.put("temperature", 0.7);
        payload.put("top_p", 1.0);
        payload.put("stream", false);

        return webClient.post()
                .uri(url)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List choices = (List) response.get("choices");
                    Map firstChoice = (Map) choices.get(0);
                    Map message = (Map) firstChoice.get("message");
                    return message.get("content").toString();
                });
    }
}
