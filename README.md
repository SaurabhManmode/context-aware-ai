🧠 Context-Aware AI Chat Application (Spring AI + Mistral)
🚀 Overview

This project is a context-aware conversational AI backend built using:

Spring Boot 3
Spring AI
Mistral AI (via Spring AI integration)
Custom in-memory session-based chat history

It replicates the behavior of frameworks like LangChain but in Java + Spring ecosystem, giving full control over memory and architecture.

🎯 Features
✅ Context-aware conversations
✅ Session-based memory (sessionId)
✅ REST API for chat
✅ Clean Spring Boot architecture
✅ Uses Mistral model via Spring AI
🚧 Easily extendable (Redis, RAG, streaming)
🏗️ Architecture
Client (Postman / UI)
↓
ChatController
↓
ChatService
↓
ChatClient (Spring AI)
↓
Mistral Model
↑
ChatHistoryService (In-memory)
📁 Project Structure
src/main/java/com/example/context_aware_ai_demo/
│
├── controller/
│   └── ChatController.java
│
├── service/
│   ├── ChatService.java
│   └── ChatHistoryService.java
│
├── dto/
│   ├── ChatRequest.java
│   └── ChatMessage.java
│
├── config/
│   └── ChatClientConfig.java
│
└── ContextAwareAiDemoApplication.java
⚙️ Prerequisites
Java 17+
Maven 3.9+
Mistral API Key
🔑 Configuration
📁 application.properties
spring.ai.mistralai.api-key=YOUR_API_KEY
spring.ai.mistralai.chat.options.model=mistral-medium
spring.ai.mistralai.chat.options.temperature=0.7
▶️ Run the Application
mvn clean install
mvn spring-boot:run

App runs on:

http://localhost:8080
🧪 API Usage
➤ Endpoint
POST /chat
➤ Request
{
"sessionId": "user123",
"input": "My name is Saurabh"
}
➤ Follow-up
{
"sessionId": "user123",
"input": "What is my name?"
}
🎯 Response
Your name is Saurabh

👉 Same sessionId → same memory

🧠 How Context Works
Each sessionId maps to a message list
Stored in memory (Map)
Full history is sent to model every request