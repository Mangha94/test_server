package com.test.lee.project.model.member.imp;

import com.test.lee.project.model.businessValue.type.ValueType;
import com.test.lee.project.model.member.MemberActionSv;
import com.test.lee.project.model.member.MemberSv;
import com.test.lee.project.model.member.data.Member;
import com.test.lee.project.model.member.dto.MemberUpdate;
import com.test.lee.project.model.member.searchData.MemberSearchData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberActionSvImp implements MemberActionSv {

    final private MemberSv memberSv;

    final private MemberRepository memberRepository;

    @Override
    public void changeValue(String valueName, ValueType valueType, boolean isDelete){
        MemberSearchData searchData = new MemberSearchData();
        MemberUpdate memberUpdate = new MemberUpdate();

        switch (valueType){
            case SPOT:
                searchData.setSpot(valueName);
                memberUpdate.setSpot(valueName);
                break;
            case position:
                searchData.setPosition(valueName);
                memberUpdate.setPosition(valueName);
                break;
        }

        List<Member> list = memberSv.gets(searchData);

        if(isDelete){
            list.forEach(m -> {
                switch (valueType){
                    case SPOT:
                        m.setSpot(null);
                        break;
                    case position:
                        m.setPosition(null);
                        break;
                }
                memberRepository.save(m);
            });
        }else{
            list.forEach(m -> {
                memberUpdate.setMemberID(m.getMemberID());
                memberSv.update(memberUpdate);
            });
        }
    }
}
