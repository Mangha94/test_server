package com.test.lee.project.model.department.dto;

import com.test.lee.project.model.department.data.Department;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DepartmentContainer {
    private String id;

    private String label;

    private Department parent;

    private List<DepartmentContainer> children;

    public DepartmentContainer() {
    }

    public DepartmentContainer(Department parent, List<Department> allList, int level) {
        if(level == 0)
            return;
        level--;
        int finalLevel = level;
        this.id = parent.getCode();
        this.label = parent.getName();
        this.parent = parent;
        this.children = allList.stream().filter(r-> r.getLevel() == parent.getLevel() + 1 && parent.getCode().equals(r.getParent())).map(r -> new DepartmentContainer(r, allList, finalLevel)).collect(Collectors.toList());
    }
}
