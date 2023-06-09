package ee.carlrobert.openai.client.completion.chat;

import ee.carlrobert.openai.client.completion.CompletionModel;
import java.util.Arrays;

public enum ChatCompletionModel implements CompletionModel {
//  GPT_3_5("gpt-3.5-turbo", "Open-ChatGPT(3.5) - Most capable model "),
//
//  GPT_3_5_SNAPSHOT("gpt-3.5-turbo-0301", "ChatGPT(3.5) - Snapshot of gpt-3.5-turbo from March 1st 2023"),
//  GPT_4("gpt-4", "ChatGPT(4.0) - Most recent model (Requires access)"),

  AZURE_GPT_3_5("gpt-3.5-turbo", "Azure-ChatGPT(3.5) - Most capable model (Default)"),

  AZURE_GPT_4("gpt-4", "Azure-ChatGPT(4.0) - Most recent model (Requires access)"),
  AZURE_GPT_4_32K("gpt-4-32k", "Azure-ChatGPT(4.0)-32k - Most recent model (Requires access)");
  private final String code;
  private final String description;

  ChatCompletionModel(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  static public ChatCompletionModel findByCode(String code) {
    return Arrays.stream(ChatCompletionModel.values())
            .filter(item -> item.getCode().equals(code))
            .findFirst().orElseThrow();
  }
}

