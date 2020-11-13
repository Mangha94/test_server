package com.test.lee.project.model.department.imp;

import com.test.lee.project.model.department.data.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    Department findByCode(String code);

    int countByParent(String parent);

    List<Department> findByParent(String parent);
}
