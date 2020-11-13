package com.test.lee.project.model.accessGroup.imp;

import com.test.lee.project.model.accessGroup.data.AccessGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessGroupRepository extends MongoRepository<AccessGroup, String> {
}
