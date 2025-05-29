package com.eagle.base.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EagleEntity {
    private String id;
    private String time;
    private String level;
    private String title;
    private String description;
}
