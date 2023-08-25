package Model;

import lombok.*;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReactPost {
    private User idUser;
    private Post idPost;
    private String reactionType;
    private Timestamp reactionDatetime;
}
