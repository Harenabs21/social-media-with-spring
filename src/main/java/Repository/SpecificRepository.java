package Repository;

import Model.ReactPost;
import lombok.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class SpecificRepository<T> {
    private Connection connection;
    public abstract List<T> getAllReactions(int idPost) throws SQLException;
    public abstract void insertNewContent (T insert) throws SQLException;
    public abstract void updateReactions(int idUser,int idPost, ReactPost newReaction) throws SQLException;
    public abstract void deleteByID(int idUser,int idPost) throws SQLException;
    public abstract void deleteByIdUser(int idUser) throws SQLException;
    public abstract void deleteByIdPost(int idPost) throws SQLException;

}
