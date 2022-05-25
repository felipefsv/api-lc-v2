package com.lc.apilc.models.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class NewWorkOrderPostMarkRequest implements Serializable {

    private String from;
    private String to;
    private String templateId;
    private NewWorkOrderTemplateModelPostMarkRequest templateModel;

}
