package ee.carlrobert.openai.client;

import ee.carlrobert.openai.client.completion.UrlCompletionModel;
import ee.carlrobert.openai.client.completion.chat.ChatCompletionModel;
import ee.carlrobert.openai.client.dashboard.DashboardClient;
import ee.carlrobert.openai.client.completion.chat.ChatCompletionClient;
import ee.carlrobert.openai.client.completion.text.TextCompletionClient;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class OpenAIClient {

  final String apiKey;
  final Proxy proxy;
  final ProxyAuthenticator proxyAuthenticator;
  final Long connectTimeout;
  final TimeUnit connectTimeoutUnit;
  final Long readTimeout;
  final TimeUnit readTimeoutUnit;
  final String chatCompletionOptionSelected;

  final String url;
  private OpenAIClient(Builder builder) {
    this.apiKey = builder.apiKey;
    this.proxy = builder.proxy;
    this.proxyAuthenticator = builder.proxyAuthenticator;
    this.connectTimeout = builder.connectTimeout;
    this.connectTimeoutUnit = builder.connectTimeoutUnit;
    this.readTimeout = builder.readTimeout;
    this.readTimeoutUnit = builder.readTimeoutUnit;
    this.chatCompletionOptionSelected = builder.chatCompletionOptionSelected;
    ChatCompletionModel byCode = ChatCompletionModel.findByCode(this.chatCompletionOptionSelected);
    this.url = byCode.getDescription();
  }

  public static class Builder {

    private final String apiKey;
    private Proxy proxy;
    private ProxyAuthenticator proxyAuthenticator;
    private Long connectTimeout;
    private TimeUnit connectTimeoutUnit;
    private Long readTimeout;
    private TimeUnit readTimeoutUnit;

    private String chatCompletionOptionSelected;


    public Builder(String apiKey) {
      this.apiKey = apiKey;
    }

    public Builder setProxy(Proxy proxy) {
      this.proxy = proxy;
      return this;
    }

    public Builder setProxyAuthenticator(ProxyAuthenticator proxyAuthenticator) {
      this.proxyAuthenticator = proxyAuthenticator;
      return this;
    }

    public Builder setConnectTimeout(Long timeout, TimeUnit unit) {
      this.connectTimeout = timeout;
      this.connectTimeoutUnit = unit;
      return this;
    }

    public Builder setReadTimeout(Long timeout, TimeUnit unit) {
      this.readTimeout = timeout;
      this.readTimeoutUnit = unit;
      return this;
    }

    public Builder setChatCompletionOptionSelected(String chatCompletionOptionSelected) {
      this.chatCompletionOptionSelected = chatCompletionOptionSelected;
      return this;
    }

    public ChatCompletionClient buildChatCompletionClient() {
      //放入url
      UrlCompletionModel byCode = UrlCompletionModel.findByCode(this.chatCompletionOptionSelected);
      String url = byCode.getDescription();
      return new ChatCompletionClient(new OpenAIClient(this),url);
    }

    public TextCompletionClient buildTextCompletionClient() {
      return new TextCompletionClient(new OpenAIClient(this));
    }

    public DashboardClient buildDashboardClient() {
      return new DashboardClient(new OpenAIClient(this));
    }
  }
}
