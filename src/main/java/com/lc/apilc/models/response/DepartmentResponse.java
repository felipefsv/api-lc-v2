package com.lc.apilc.models.response;

import com.lc.apilc.models.entity.User;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class DepartmentResponse {
    private UUID id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<User> users;
}
