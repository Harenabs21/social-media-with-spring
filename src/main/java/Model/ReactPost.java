package Model;

import lombok.*;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReactPost {
    private int idUser;
    private int idPost;
    private String reactionType;
    private Timestamp reactionDatetime;
}
