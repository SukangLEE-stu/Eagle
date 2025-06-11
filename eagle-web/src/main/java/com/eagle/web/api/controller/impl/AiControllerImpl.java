package com.eagle.web.api.controller.impl;

import com.eagle.base.ai.dao.PhotoPostItem;
import com.eagle.base.ai.dao.PhotoRequestDTO;
import com.eagle.base.ai.service.PhotoPostItemService;
import com.eagle.base.dao.HttpResult;
import com.eagle.web.ai.AiService;
import com.eagle.web.ai.TextModelType;
import com.eagle.web.api.controller.def.AiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AiControllerImpl implements AiController {
    @Autowired
    private AiService aiService;

    @Autowired
    private PhotoPostItemService photoPostItemService;

    @Override
    public HttpResult getChatModels() {
        return HttpResult.builder().code(200).message("success").data(TextModelType.getModels()).build();
    }

    @Override
    public HttpResult chat(String question, String modelType) {
        return HttpResult.builder().code(200).message("success")
                .data(aiService.chat(question, TextModelType.getModelByName(modelType))).build();
    }

    @Override
    public HttpResult getPhotoModels() {
        return HttpResult.builder().code(200).message("success").data(Collections.emptyList()).build();
    }

    @Override
    public HttpResult getPhotoTasks() {
        return HttpResult.builder().code(200).message("success").data(photoPostItemService.getList(50)).build();
    }

    @Override
    public HttpResult photo(@RequestBody PhotoRequestDTO photoRequestDTO) {
        int width = photoRequestDTO.getWidth();
        int height = photoRequestDTO.getHeight();
        String prompt = photoRequestDTO.getPrompt();
        if (!(width >=512 && width <= 1440) || !(height >=512 && height <= 1440)) {
            return HttpResult.builder().code(400).message("error").message("width and height must be between 512 and 1440").build();
        }
        PhotoPostItem item = aiService.photoRequest(prompt, width, height);
        if (item != null) {
            return HttpResult.builder().code(200).message("success")
                    .data(item).build();
        }
        return HttpResult.builder().code(400).message("error").message("query failure").build();
    }

    @Override
    public HttpResult photo(String taskId) {
        return HttpResult.builder().code(200).message("success")
                .data(aiService.photoFetch(taskId)).build();
    }
}
