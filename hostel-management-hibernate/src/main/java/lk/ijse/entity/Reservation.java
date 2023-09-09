package lk.ijse.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "res_id")
    private String reserveID;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_type_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "stu_id", nullable = false)
    private Student student;

    @Column (name = "date")
    @CreationTimestamp
    private Date date;

    @Column (name = "status")
    private String status;
}
