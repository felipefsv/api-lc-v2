package com.lc.apilc.clients;

import com.lc.apilc.models.request.NewWorkOrderPostMarkRequest;
import com.lc.apilc.models.response.PostMarkEmailWithTemplateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "postMark", url="${postmark.url}")
public interface PostMarkClient {

    @PostMapping(value = "email/withTemplate")
    PostMarkEmailWithTemplateResponse deliverEmailWithTemplate(@RequestHeader("X-Postmark-Server-Token") String token, NewWorkOrderPostMarkRequest newWorkOrderPostMarkRequest);
}
