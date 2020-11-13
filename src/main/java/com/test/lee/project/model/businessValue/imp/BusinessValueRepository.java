package com.test.lee.project.model.businessValue.imp;

import com.test.lee.project.model.businessValue.data.BusinessValue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusinessValueRepository extends MongoRepository<BusinessValue, String> {
}
