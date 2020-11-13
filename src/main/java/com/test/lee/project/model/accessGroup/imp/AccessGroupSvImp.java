package com.test.lee.project.model.accessGroup.imp;

import com.test.lee.project.common.MongoSupportSv;
import com.test.lee.project.lib.Util;
import com.test.lee.project.model.accessGroup.AccessGroupSv;
import com.test.lee.project.model.accessGroup.data.AccessGroup;
import com.test.lee.project.model.accessGroup.dto.RegistAccessGroup;
import com.test.lee.project.model.accessGroup.dto.UpdateAccessGroup;
import com.test.lee.project.model.accessGroup.searchData.AccessGroupSearchData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessGroupSvImp implements AccessGroupSv {

    final private AccessGroupRepository accessGroupRepository;

    final private MongoSupportSv mongoSupportSv;

    @Override
    public AccessGroup insert(RegistAccessGroup registAccessGroup) {

        AccessGroup accessGroup = new AccessGroup();

        Util.myCopyProperties(registAccessGroup, accessGroup);

        return accessGroupRepository.insert(accessGroup);
    }

    @Override
    public AccessGroup update(UpdateAccessGroup updateAccessGroup) {

        AccessGroup accessGroup = accessGroupRepository.findById(updateAccessGroup.getId()).orElse(null);

        if (accessGroup == null) return null;

        Util.myCopyProperties(updateAccessGroup, accessGroup);

        accessGroupRepository.save(accessGroup);


        return accessGroupRepository.save(accessGroup);
    }

    @Override
    public boolean delete(String id) {
        AccessGroup accessGroup = accessGroupRepository.findById(id).orElse(null);

        if(accessGroup == null) return false;

        accessGroupRepository.deleteById(id);

        return true;
    }

    @Override
    public List<AccessGroup> gets(AccessGroupSearchData searchData) {
        return mongoSupportSv.getAllList(AccessGroup.class, searchData.makeQuery());
    }

    @Override
    public AccessGroup get(String id){
        return accessGroupRepository.findById(id).orElse(null);
    }
}
