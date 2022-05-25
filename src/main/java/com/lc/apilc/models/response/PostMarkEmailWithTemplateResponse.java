package com.lc.apilc.models.response;

import lombok.Data;

@Data
public class PostMarkEmailWithTemplateResponse {
    private String To;
    private String SubmittedAt;
    private String MessageID;
    private Integer ErrorCode;
    private String Message;
}
