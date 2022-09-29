package com.sizxero.crowbus.dto;

import java.util.List;
import lombok.Data;
import lombok.Builder;
@Data
@Builder
public class ResponseDTO<T> {
    private String error;
    private List<T> data;
}