package com.test.lee.project.model.member.imp;

import com.test.lee.project.common.MongoSupportSv;
import com.test.lee.project.lib.StrLib;
import com.test.lee.project.model.member.MemberSv;
import com.test.lee.project.model.member.data.Member;
import com.test.lee.project.model.member.dto.LoginData;
import com.test.lee.project.model.member.dto.MemberUpdate;
import com.test.lee.project.model.member.dto.RegistMember;
import com.test.lee.project.model.member.searchData.MemberSearchData;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberSvImp implements MemberSv {

    final private MemberRepository memberRepository;

    final private MongoSupportSv mongoSupportSv;

    @Override
    public String login(LoginData loginData){
        Member member = memberRepository.findById(loginData.getMemberID()).orElse(null);

        if(member == null) return null;

        if(!BCrypt.checkpw(loginData.getPassword(), member.getPasswd()))
            return null;

        String tempToken = StrLib.generateToken();

        return Base64.encodeBase64String(tempToken.getBytes());
    }

    @Override
    public Member get(String memberID){
        return memberRepository.findById(memberID).orElse(null);
    }

    @Override
    public List<Member> gets(MemberSearchData searchData){
        return mongoSupportSv.getAllList(Member.class, searchData.makeQuery());
    }

    @Override
    public Member insert(RegistMember newMember){
        Member member = new Member().insertMember(newMember);

        //todo 비밀번호 암호화
        member.setPasswd (BCrypt.hashpw (member.getPasswd(), BCrypt.gensalt ()));

        return memberRepository.insert(member);
    }

    @Override
    public Member update(MemberUpdate memberUpdate){

        Member save = memberRepository.findById(memberUpdate.getMemberID()).orElse(null);

        if(save == null) return null;

        return memberRepository.save(save.setMember(save, memberUpdate));
    }

    @Override
    public Member update(MemberUpdate memberUpdate, boolean isDelete){
        Member saveMember = memberRepository.findById(memberUpdate.getMemberID()).orElse(null);

        if(saveMember == null) return null;

        return memberRepository.save(saveMember.setMember(saveMember, memberUpdate));
    }

    @Override
    public boolean delete(String memberID){
        Member member = memberRepository.findById(memberID).orElse(null);

        if(member == null) return false;

        memberRepository.delete(member);
        return true;
    }

}
