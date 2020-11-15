package com.test.lee.project.model.member;

import com.test.lee.project.model.member.data.Member;
import com.test.lee.project.model.member.dto.LoginData;
import com.test.lee.project.model.member.dto.MemberUpdate;
import com.test.lee.project.model.member.dto.RegistMember;
import com.test.lee.project.model.member.searchData.MemberSearchData;

import java.util.List;

public interface MemberSv {

    String login(LoginData loginData);

    /**
     * 멤버를 가지고 온다
     * @param memberID 멤버아이디
     * @return Member
     */
    Member get(String memberID);

    /**
     * 멤버리스트를 가지고 온다
     * @param searchData 검색데이터
     * @return List<Member>
     */
    List<Member> gets(MemberSearchData searchData);

    /**
     * 멤버 등록한다
     * @param member 새로등록하는 데이터
     * @return 등록된 멤버
     */
    Member insert(RegistMember member);

    /**
     * 멤버 업데이트
     * @param memberUpdate 업데이트용 데이터
     * @return Member
     */
    Member update(MemberUpdate memberUpdate);

    Member update(MemberUpdate memberUpdate, boolean isDelete);

    /**
     * 멤버를 삭제한다
     * @param memberID 멤버아이디
     * @return 삭제여부
     */
    boolean delete(String memberID);

}
