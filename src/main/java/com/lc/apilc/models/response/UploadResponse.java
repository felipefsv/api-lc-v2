package com.lc.apilc.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {
    private String message;
    private String fileName;
}
