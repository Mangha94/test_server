package com.test.lee.project.model.department.data;

import com.test.lee.project.model.department.dto.RegistDepartment;
import com.test.lee.project.model.department.dto.UpdateDepartment;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Department {

    @Id
    private String id;

    /**
     * 부서이름
     */
    private String name;

    /**
     * 부서코드
     */
    private String code;

    /**
     * 순서
     */
    private int seq;

    /**
     * 레벨
     */
    private int level;

    /**
     * 상위 부서
     */
    private String parent;

    public Department() {
    }

    public Department(RegistDepartment registDepartment) {
        this.name = registDepartment.getName();
        this.code = registDepartment.getCode();
        this.seq = registDepartment.getSeq();
        this.level = registDepartment.getLevel();
        this.parent = registDepartment.getParent();
    }

    public Department setData(Department department, UpdateDepartment updateDepartment){
        if(updateDepartment.getCode() != null)
            department.setCode(updateDepartment.getCode());

        if(updateDepartment.getName() != null)
            department.setName(updateDepartment.getName());

        return department;
    }
}
