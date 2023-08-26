package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "rooms")
public class Room {

    @Id
    @Column(name = "room_type_id")
    private String typeId;

    @Column(name = "room_type")
    private String type;

    @Column(name = "key_money")
    private String keyMoney;

    @Column(name = "qty")
    private int qty;

    @OneToMany (cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();
}
