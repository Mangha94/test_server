package com.test.lee;

import com.test.lee.project.model.department.DepartmentSv;
import com.test.lee.project.model.department.data.Department;
import com.test.lee.project.model.department.dto.DepartmentContainer;
import com.test.lee.project.model.department.dto.RegistDepartment;
import com.test.lee.project.model.department.searchData.DepartmentSearchData;
import com.test.lee.project.model.member.MemberSv;
import com.test.lee.project.model.member.dto.RegistMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("local")
public class test {

    @Autowired MemberSv memberSv;

    @Autowired
    DepartmentSv departmentSv;

    @Test
    public void sdf(){
        RegistMember registMember = new RegistMember();
        registMember.setMemberID("lshs19");
        registMember.setName("이승현");
        registMember.setPassword("a0000000");
        registMember.setPhone("01047615461");
        registMember.setPosition(null);
        registMember.setSpot(null);
        System.out.println(memberSv.insert(registMember));
    }

    @Test
    public void setDepartment(){
        for(int i = 0; i < 4; i++) {
            RegistDepartment registDepartment = new RegistDepartment();
            registDepartment.setSeq(i + 1);
            registDepartment.setLevel(i);
            registDepartment.setName("test" + i);
            registDepartment.setCode("test_" + i);
            if(i == 0)
                registDepartment.setParent(null);
            else
                registDepartment.setParent("test_" + (i - 1));

            departmentSv.insert(registDepartment);
        }

        RegistDepartment registDepartment = new RegistDepartment();
        registDepartment.setSeq(5);
        registDepartment.setLevel(0);
        registDepartment.setName("test");
        registDepartment.setCode("test_12");
        registDepartment.setParent(null);

        departmentSv.insert(registDepartment);
    }

    @Test
    public void getDepartment(){
        DepartmentSearchData searchData = new DepartmentSearchData();

        List<Department> list = departmentSv.gets(searchData);

        List<Department> temp = list.stream().filter(r -> r.getLevel() == 0).collect(Collectors.toList());

        List<DepartmentContainer> set = new ArrayList<>();

        temp.forEach(r -> {
            set.add(new DepartmentContainer(r, list, 4));
        });

        System.out.println(set);
    }
}
