package Repository;
import Model.Post;
import Model.ReactPost;
import Model.User;
import lombok.*;

import java.sql.*;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReactPostRepository extends SpecificRepository<ReactPost>{
    private ReactPost reacts;
    public Optional<ReactPost> getAllReactions(int id) throws SQLException{
        String sql = "SELECT * FROM react_post WHERE id_post = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int idPost = resultSet.getInt("id_post");
                int idUser = resultSet.getInt("id_account");
                String reactionType = resultSet.getString("reaction_type");
                Timestamp reactionDatetime = resultSet.getTimestamp("reaction_datetime");
                User user = new User(idUser);
                Post post = new Post(idPost);
                ReactPost reactions = new ReactPost(user,post,reactionType,reactionDatetime);
                return Optional.of(reactions);
            }
        }
        return Optional.empty();
    }

    @Override
    public void insertNewContent(ReactPost insert) throws SQLException {
        String sql = "INSERT INTO react_post(id_post, id_account, reaction_type) VALUES (?, ?, ?)";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,insert.getIdPost().getId_post());
            statement.setInt(2,insert.getIdPost().getId_post());
            statement.setString(3,insert.getReactionType());
            statement.executeUpdate();
        }

    }

    @Override
    public void updateByID(ReactPost object) throws SQLException {
        String sql = "UPDATE react_post SET reaction_type = ? WHERE id_post = ? AND id_account = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setString(1,object.getReactionType());
            statement.setInt(2,object.getIdPost().getId_post());
            statement.setInt(3,object.getIdUser().getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteByID(ReactPost object) throws SQLException {
        String sql = "DELETE FROM react_post WHERE id_post = ? AND id_account = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,object.getIdPost().getId_post());
            statement.setInt(2,object.getIdUser().getId());
            statement.executeUpdate();
        }
    }
}
