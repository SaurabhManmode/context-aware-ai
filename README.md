
# 🧠 Context-Aware AI Chat Application

### (Spring AI + Mistral)

## 🚀 Overview
This project is a context-aware conversational AI backend built with **Spring Boot 3** and **Spring AI**. It leverages the **Mistral AI** model to provide intelligent responses while maintaining a custom in-memory session-based chat history.

By replicating the memory patterns of frameworks like LangChain within the Java/Spring ecosystem, this project offers full control over conversation state and backend architecture.

## 🎯 Features
*   ✅ **Context-Aware Conversations**: Remembers previous interactions within the same session.
*   ✅ **Session-Based Memory**: Uses `sessionId` to isolate chat histories.
*   ✅ **REST API**: Simple POST endpoint for easy integration with UIs or Postman.
*   ✅ **Mistral Integration**: Seamlessly connects to Mistral via Spring AI.
*   ✅ **Extensible**: Built to easily support Redis for persistent memory, RAG (Retrieval-Augmented Generation), or streaming responses.

## 🏗️ Architecture
The flow of data through the application:

`Client (Postman/UI)` ➔ `ChatController` ➔ `ChatService` ➔ `ChatClient (Spring AI)` ➔ `Mistral Model`
**Note**: The `ChatService` interacts with `ChatHistoryService` to inject conversation history into every prompt.



---

## 📁 Project Structure
```text
src/main/java/com/example/context_aware_ai_demo/
│
├── config/         # Spring AI and Bean configurations
├── controller/     # REST Endpoints
├── dto/            # Data Transfer Objects (Request/Response)
├── service/        # Business logic and History Management
└── Application.java
```

## ⚙️ Prerequisites
*   **Java 17** or higher
*   **Maven 3.9+**
*   **Mistral API Key** (Get one at [console.mistral.ai](https://console.mistral.ai/))

## 🔑 Configuration
Update your `src/main/resources/application.properties`:

```properties
spring.ai.mistralai.api-key=YOUR_API_KEY
spring.ai.mistralai.chat.options.model=mistral-medium
spring.ai.mistralai.chat.options.temperature=0.7
```

## ▶️ Getting Started
1. **Clone the repository**:
   ```bash
   git clone <repo-url>
   cd context-aware-ai-demo
   ```
2. **Build and Run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   The application will be available at: `http://localhost:8080`

---

## 🧪 API Usage

### 1. Initialize Context
**Endpoint:** `POST /chat`  
**Payload:**
```json
{
  "sessionId": "session_001",
  "input": "My name is Saurabh."
}
```

### 2. Follow-up (Contextual)
**Endpoint:** `POST /chat`  
**Payload:**
```json
{
  "sessionId": "session_001",
  "input": "What is my name?"
}
```
**Expected Response:**
> "Your name is Saurabh."

---

## 🧠 How Context Works
1.  **Storage**: Each `sessionId` is mapped to a `List<ChatMessage>` in an in-memory `Map`.
2.  **State**: When a new request arrives, the `ChatHistoryService` retrieves all previous messages for that ID.
3.  **Prompting**: The entire history is sent to the Mistral model, allowing it to "remember" the conversation flow.
```
