package com.eagle.base.ai.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoRequestDTO {
    private String prompt;
    private int width;
    private int height;
    private String modelType;
}
