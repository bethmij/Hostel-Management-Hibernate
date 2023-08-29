package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.InputStream;
import java.sql.Blob;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private String userID;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "profile_pic")
    private byte[] image;
}
