package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column (name = "dob", columnDefinition = "DATE")
    private LocalDate dob;

    @Column (name = "gender")
    private String gender;

    @OneToMany (cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "student")
    private List<Reservation> reservations = new ArrayList<>();

    public Student(String studentID) {
        this.studentID = studentID;
    }

    public Student(String studentID, String name, String address, int tel1, int tel2, String email, LocalDate dob, String gender) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
    }

}
