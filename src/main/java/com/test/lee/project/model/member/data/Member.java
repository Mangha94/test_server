package com.test.lee.project.model.member.data;

import com.test.lee.project.common.data.RegModDate;
import com.test.lee.project.model.member.dto.MemberUpdate;
import com.test.lee.project.model.member.dto.RegistMember;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Member {

    @Id
    private String memberID;

    private String passwd;

    private String name;

    private String phone;

    private String spot;

    private String position;

    RegModDate regModDate;

    public Member() {
    }

    public Member(String username) {
        this.memberID = username;
    }

    public Member setMember(Member member, MemberUpdate memberUpdate){
        if(memberUpdate.getPhone() != null)
            member.setPhone(memberUpdate.getPhone());

        if(memberUpdate.getName() != null)
            member.setName(memberUpdate.getName());

        if(memberUpdate.getPosition() != null)
            member.setPosition(memberUpdate.getPosition());

        if(memberUpdate.getSpot() != null)
            member.setSpot(memberUpdate.getSpot());

        member.getRegModDate().setModDate(new Date());

        return member;
    }

    public Member insertMember(RegistMember newMember){
        Member member = new Member();

        if(newMember.getMemberID() != null)
            member.setMemberID(newMember.getMemberID());

        if(newMember.getName() != null)
            member.setName(newMember.getName());

        if(newMember.getPassword() != null)
            member.setPasswd(newMember.getPassword());

        if(newMember.getPhone() != null)
            member.setPhone(newMember.getPhone());

        if(newMember.getPosition() != null)
            member.setPosition(newMember.getPosition());

        if(newMember.getSpot() != null)
            member.setSpot(newMember.getSpot());

        member.setRegModDate(new RegModDate(new Date(), new Date()));

        return member;
    }
}
