package com.eagle.web.api.controller.def;


import com.eagle.base.ai.dao.AiModelVo;
import com.eagle.base.ai.dao.PhotoRequestDTO;
import com.eagle.base.constants.WebConstants;
import com.eagle.base.dao.HttpResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(WebConstants.WEB_API_PREFIX_V1 + "/ai")
public interface AiController {
    @GetMapping("/chat/models")
    HttpResult getChatModels();

    @PostMapping("/chat/query")
    HttpResult chat(String question, String modelType);

    @GetMapping("/photo/models")
    HttpResult getPhotoModels();
    @GetMapping("/photo/tasks")
    HttpResult getPhotoTasks();

    @PostMapping("/photo/query")
    HttpResult photo(@RequestBody PhotoRequestDTO photoRequestDTO);

    @GetMapping("/photo/query/{taskId}")
    HttpResult photo(@PathVariable String taskId);
}
