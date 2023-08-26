package lk.ijse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table (name = "student")
public class Student {

    @Id
    @Column (name = "stu_id")
    private String studentID;

    @Column (name = "stu_name")
    private String name;

    @Column (name = "stu_address", columnDefinition = "TEXT")
    private String address;

    @Column (name = "contact_1")
    private int tel1;

    @Column (name = "contact_2")
    private int tel2;

    @Column (name = "email")
    private String email;

    @Column (name = "dob")
    @CreationTimestamp
    private Date dob;

    @Column (name = "gender")
    private String gender;

}
