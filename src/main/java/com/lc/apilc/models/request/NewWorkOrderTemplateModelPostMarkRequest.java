package com.lc.apilc.models.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class NewWorkOrderTemplateModelPostMarkRequest implements Serializable {

    private String clientName;
    private String osNumber;
    private String createdAt;
    private String createdBy;

}
