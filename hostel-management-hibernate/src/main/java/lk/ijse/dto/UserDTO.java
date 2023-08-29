package lk.ijse.dto;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private String userID;
    private String name;
    private String userName;
    private String password;
    private String Email;
    private byte[] profilePic;
    
}
