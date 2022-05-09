package com.lc.apilc.models.request;

import com.lc.apilc.models.entity.Department;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotNull
    private boolean isAdmin;
    @NotNull
    private Department department;
}
