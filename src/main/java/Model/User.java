package Model;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDate birthday;
    private char gender;
    private String profilePicture;
    private String email;
    private String password;
    public User(String nickname) {
        this.nickname = nickname;
    }
    public User(int id) {
        this.id = id;
    }
    public User(int id, String firstName, String lastName, String nickname, LocalDate birthday, char gender, String profilePicture){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.birthday = birthday;
        this.gender = gender;
        this.profilePicture = profilePicture;
    }
}
