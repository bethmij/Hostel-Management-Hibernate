package lk.ijse.entity;



import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "res_id")
    private String reserveID;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_type_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "stu_id")
    private Student student;

    @Column (name = "date")
    @CreationTimestamp
    private Date date;

    @Column (name = "status")
    private String status;
}
