package model;

import lombok.Data;

@Data
public class ApiResult {
    private Integer code;
    private String type;
    private String message;
}