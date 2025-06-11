package com.eagle.web.ai;

import com.eagle.base.ai.dao.AiModelVo;

import java.util.Arrays;
import java.util.List;

public enum TextModelType {
    QWEN_PLUS("qwen-plus", "qwen-plus"),
    QWEN_LITE("qwen-lite", "qwen-lite"),
    QWEN_7B("qwen-7b", "qwen-7b");
    private final String modelName;
    private final String modelType;

    TextModelType(String modelName, String modelType) {
        this.modelName = modelName;
        this.modelType = modelType;
    }

    public static TextModelType getModelByName(String modelType) {
        return Arrays.stream(TextModelType.values())
                .filter(model -> model.getModelName().equals(modelType))
                .findFirst()
                .orElse(null);
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelType() {
        return modelType;
    }

    public static List<AiModelVo> getModels() {
        return Arrays.asList(
                new AiModelVo(QWEN_PLUS.getModelName(), QWEN_PLUS.getModelType()),
                new AiModelVo(QWEN_LITE.getModelName(), QWEN_LITE.getModelType()),
                new AiModelVo(QWEN_7B.getModelName(), QWEN_7B.getModelType())
        );
    }
}
