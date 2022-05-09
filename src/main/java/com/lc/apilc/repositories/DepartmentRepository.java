package com.lc.apilc.repositories;

import com.lc.apilc.models.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DepartmentRepository  extends JpaRepository<Department, UUID> {

}
