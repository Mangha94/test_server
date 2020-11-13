package com.test.lee.project.model.department.imp;

import com.test.lee.project.common.MongoSupportSv;
import com.test.lee.project.model.department.DepartmentSv;
import com.test.lee.project.model.department.data.Department;
import com.test.lee.project.model.department.dto.RegistDepartment;
import com.test.lee.project.model.department.dto.UpdateDepartment;
import com.test.lee.project.model.department.searchData.DepartmentSearchData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentSvImp implements DepartmentSv {

    final private DepartmentRepository departmentRepository;

    final private MongoSupportSv mongoSupportSv;

    @Override
    public Department insert(RegistDepartment registDepartment) {

        Department department = new Department();

        //부모가 없다면 최상위, 순서는 맨끝
        if(registDepartment.getParent() == null){
            registDepartment.setSeq((int) departmentRepository.count());
            registDepartment.setLevel(0);
        }
        //부모가 있다면 부모레벨 + 1, 순서는 부모 자식들 최고 seq + 1 하위 seq 는 + 1처리
        else{
            Department parent = departmentRepository.findByCode(registDepartment.getParent());

            registDepartment.setLevel(parent.getLevel() + 1);

            List<Department> child = departmentRepository.findByParent(registDepartment.getParent());

            int seq = 0;

            if(child.size() > 0){
                seq = child.stream().max(Comparator.comparing(Department::getSeq)).get().getSeq();
            }else{
                seq = parent.getSeq();
            }

            registDepartment.setSeq(seq + 1);

            DepartmentSearchData searchData = new DepartmentSearchData();
            searchData.setSeq(seq + 1);
            List<Department> list = gets(searchData);
            list.forEach(r -> {
                int beforeSeq = r.getSeq();
                r.setSeq(beforeSeq + 1);
                departmentRepository.save(r);
            });

        }
        department = departmentRepository.insert(new Department(registDepartment));

        return department;
    }

    @Override
    public Department update(UpdateDepartment updateDepartment) {

        Department targetDepartment = get(updateDepartment.getId());
        if(targetDepartment == null) return null;

        Department saveDepartment = departmentRepository.save(targetDepartment.setData(targetDepartment, updateDepartment));

        //코드가 바뀌었다, 하위 멤버들을 수정해준다.
        if(updateDepartment.getCode() != null){
            List<Department> list = departmentRepository.findByParent(targetDepartment.getParent());

            list.forEach(r -> {
                r.setParent(updateDepartment.getCode());
                departmentRepository.save(r);
            });
        }

        return saveDepartment;
    }

    @Override
    public boolean delete(String id) {

        //todo 예외처리 하면 조을거같음
        Department deleteDepartment = get(id);

        //삭제하려는 부서가 존재하지 않음
        if(deleteDepartment == null) return false;

        List<Department> list = departmentRepository.findByParent(deleteDepartment.getParent());

        //삭제하려는 부서에 자식이 있음
        if(list.size() > 0) return false;

        departmentRepository.deleteById(id);

        //삭제 완료후 비어있는 seq 를 채워준다.
        DepartmentSearchData searchData = new DepartmentSearchData();
        searchData.setSeq(deleteDepartment.getSeq());
        List<Department> saveList = gets(searchData);
        saveList.forEach(r -> {
            int beforeSeq = r.getSeq();
            r.setSeq(beforeSeq - 1);
            departmentRepository.save(r);
        });

        return true;
    }

    @Override
    public List<Department> gets(DepartmentSearchData searchData) {

        searchData.setOrderVal("seq_ASC");

        return mongoSupportSv.getAllList(Department.class, searchData.makeQuery());
    }

    @Override
    public Department get(String id){
        return departmentRepository.findById(id).orElse(null);
    }
}
