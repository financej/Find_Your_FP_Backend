package com.metlife.team09.domain.openai.application;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OpenAiConfig {
    @Value("${GPT4.end-point}")
    private String endPoint;

    @Value("${GPT4.key1}")
    private String key1;

    @Value("${GPT4.model.id}")
    private String modelId;

    @Value("${GPT4.model.deployment-or-name}")
    public String modelName;

    public OpenAIClient getOpenAiClient(){
        return new OpenAIClientBuilder()
                .endpoint(endPoint)
                .credential(new AzureKeyCredential(key1))
                .buildClient();
    }

    public String getGptResult(){

    }

}
