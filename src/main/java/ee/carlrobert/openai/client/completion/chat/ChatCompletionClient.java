package ee.carlrobert.openai.client.completion.chat;

import ee.carlrobert.openai.client.ClientCode;
import ee.carlrobert.openai.client.OpenAIClient;
import ee.carlrobert.openai.client.completion.CompletionClient;
import ee.carlrobert.openai.client.completion.CompletionEventListener;
import ee.carlrobert.openai.client.completion.chat.request.ChatCompletionRequest;
import okhttp3.sse.EventSource;

public class ChatCompletionClient extends CompletionClient {

  public ChatCompletionClient(OpenAIClient client,String url) {
//    super(client, "https://api.openai.com/v1/chat/completions");

//    super(client, "https://api.openai.com/v1/chat/completions");
    super(client, url);
  }

  @Override
  protected ChatCompletionEventSourceListener getEventListener(CompletionEventListener listeners) {
    return new ChatCompletionEventSourceListener(listeners);
  }

  @Override
  protected ClientCode getClientCode() {
    return ClientCode.CHAT_COMPLETION;
  }

  public EventSource stream(ChatCompletionRequest requestBody, CompletionEventListener listeners) {
    return createNewEventSource(requestBody, listeners);
  }
}
