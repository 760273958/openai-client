package ee.carlrobert.openai.client.completion;

import java.util.Arrays;

/**
 * URl地址
 */
public enum UrlCompletionModel implements CompletionModel {
    AZURE_GPT_3_5("gpt-3.5-turbo", "https://td.openai.azure.com/openai/deployments/gpt-35-turbo/chat/completions?api-version=2023-03-15-preview"),

    AZURE_GPT_4("gpt-4", "https://td.openai.azure.com/openai/deployments/gpt-4/chat/completions?api-version=2023-03-15-preview"),
    AZURE_GPT_4_32K("gpt-4-32k", "https://td.openai.azure.com/openai/deployments/gpt-4-32k/chat/completions?api-version=2023-03-15-preview");

    private final String code;
    private final String url;

    UrlCompletionModel(String code, String url) {
        this.code = code;
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return url;
    }

    static public UrlCompletionModel findByCode(String code) {
        return Arrays.stream(UrlCompletionModel.values())
                .filter(item -> item.getCode().equals(code))
                .findFirst().orElseThrow();
    }
}
