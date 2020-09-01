package org.example.docs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Users")
public class Users {
    @Id
    private Long id;

    //@Indexed(unique = true)
    @Field(value = "Emp_No")
    private String empNo;

    @Field(value = "Fullname")
    private String fullName;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setNewUser(Long id, String empNo, String fullName){
        this.id = id;
        this.empNo = empNo;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
    public Long getId() {
        return id;
    }
    public String getEmpNo() {
        return empNo;
    }

    @Override
    public String toString(){
        return "id: " + this.id + " ,emoNo:" + empNo
                + ", fullName: " + this.fullName;
    }
}


