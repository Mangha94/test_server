package com.test.lee.project.model.member.imp;

import com.test.lee.project.model.member.data.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {
}
