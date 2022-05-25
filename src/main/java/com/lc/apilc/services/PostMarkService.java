package com.lc.apilc.services;

import com.lc.apilc.clients.PostMarkClient;
import com.lc.apilc.configuration.PostMarkConfig;
import com.lc.apilc.models.entity.WorkOrder;
import com.lc.apilc.models.request.NewWorkOrderPostMarkRequest;
import com.lc.apilc.models.request.NewWorkOrderTemplateModelPostMarkRequest;
import com.lc.apilc.models.response.PostMarkEmailWithTemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostMarkService {

    @Autowired
    private PostMarkClient postMarkClient;

    @Autowired
    private PostMarkConfig postMarkConfig;

    public PostMarkEmailWithTemplateResponse sendNewWorkOrderEmail(WorkOrder workOrder) {
        NewWorkOrderTemplateModelPostMarkRequest newWorkOrderTemplateModelPostMarkRequest = NewWorkOrderTemplateModelPostMarkRequest.builder()
                .osNumber(String.valueOf(workOrder.getId()))
                .clientName(workOrder.getClient().getName())
                .createdAt(workOrder.getCreatedAt().toString())
                .createdBy(workOrder.getUser().getName())
                .build();

        NewWorkOrderPostMarkRequest newWorkOrderPostMarkRequest = NewWorkOrderPostMarkRequest
                .builder()
                .from("")
                .to("")
                .templateId("")
                .templateModel(newWorkOrderTemplateModelPostMarkRequest)
                .build();

        return postMarkClient.deliverEmailWithTemplate(postMarkConfig.getPostMarkKey(), newWorkOrderPostMarkRequest);
    }

}
