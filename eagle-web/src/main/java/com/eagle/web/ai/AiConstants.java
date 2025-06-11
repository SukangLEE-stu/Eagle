package com.eagle.web.ai;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AiConstants {
    @Value("${ai.api.key}")
    private String apiKey;

    @Value("${ai.api.photo.post}")
    private String photoServicePostUrl;

    @Value("${ai.api.photo.get}")
    private String photoServiceGetUrl;

    @Value("${ai.api.text.post}")
    private String textServicePostUrl;
}
