package Model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    private int id_post;
    private User id_account;
    private LocalDate postingDate;
    private LocalTime postingTime;
    private String postContent;
    private String postPhoto;

    public Post(int id_post){
        this.id_post = id_post;
    }

}
