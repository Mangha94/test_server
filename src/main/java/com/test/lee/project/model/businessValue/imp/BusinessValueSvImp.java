package com.test.lee.project.model.businessValue.imp;

import com.test.lee.project.common.MongoSupportSv;
import com.test.lee.project.model.businessValue.BusinessValueSv;
import com.test.lee.project.model.businessValue.data.BusinessValue;
import com.test.lee.project.model.businessValue.dto.RegistBusinessValue;
import com.test.lee.project.model.businessValue.searchData.ValueSearchData;
import com.test.lee.project.model.member.MemberActionSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessValueSvImp implements BusinessValueSv {

    final private BusinessValueRepository businessValueRepository;

    final private MongoSupportSv mongoSupportSv;

    final private MemberActionSv memberActionSv;

    @Override
    public BusinessValue insert(RegistBusinessValue registBusinessValue){
        return businessValueRepository.insert(new BusinessValue(registBusinessValue));
    }

    @Override
    public BusinessValue update(String id, RegistBusinessValue registBusinessValue){

        BusinessValue businessValue = get(id);
        if (businessValue == null) return null;

        BusinessValue saveValue = businessValueRepository.save(new BusinessValue().update(businessValue, registBusinessValue));

        //해당 벨류인 멤버들 수정해야함
        memberActionSv.changeValue(saveValue.getName(), saveValue.getValueType(), false);

        return saveValue;
    }

    @Override
    public boolean delete(String id){

        BusinessValue businessValue = get(id);

        if(businessValue == null) return false;

        businessValueRepository.deleteById(id);

        //해당 벨류인 멤버들 수정해야함
        memberActionSv.changeValue(businessValue.getName(), businessValue.getValueType(), true);

        return true;
    }

    @Override
    public List<BusinessValue> gets(ValueSearchData searchData){

        searchData.setOrderVal("seq_ASC");

        return mongoSupportSv.getAllList(BusinessValue.class, searchData.makeQuery());
    }

    @Override
    public BusinessValue get(String id){
        return businessValueRepository.findById(id).orElse(null);
    }
}
