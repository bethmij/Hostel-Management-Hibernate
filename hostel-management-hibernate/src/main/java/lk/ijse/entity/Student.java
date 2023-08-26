package lk.ijse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "student")
public class Student {

    @Id
    @Column (name = "stu_id")
    private String studentID;


}
