package Repository;
import Model.ReactPost;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ReactPostRepository extends SpecificRepository<ReactPost>{
    public ReactPostRepository(Connection connection){super(connection);}
    public List<ReactPost> getAllReactions(int idPost) throws SQLException{
        List<ReactPost> allReactions = new ArrayList<>(0);
        String sql = "SELECT * FROM react_post WHERE id_post = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,idPost);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int postId = resultSet.getInt("id_post");
                int idUser = resultSet.getInt("id_account");
                String reactionType = resultSet.getString("reaction_type");
                Timestamp reactionDatetime = resultSet.getTimestamp("reaction_datetime");
                ReactPost reactions = new ReactPost(idUser,postId,reactionType,reactionDatetime);
                allReactions.add(reactions);
            }
        }
        return allReactions;
    }

    @Override
    public void insertNewContent(ReactPost insert) throws SQLException {
        String sql = "INSERT INTO react_post(id_post, id_account, reaction_type) VALUES (?, ?, ?)";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,insert.getIdPost());
            statement.setInt(2,insert.getIdUser());
            statement.setString(3,insert.getReactionType());
            statement.executeUpdate();
        }

    }

    @Override
    public void updateReactions(int idUser, int idPost, ReactPost newReaction) throws SQLException {
        String sql = "UPDATE react_post SET reaction_type = ? WHERE id_post = ? AND id_account = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setString(1,newReaction.getReactionType());
            statement.setInt(2,idPost);
            statement.setInt(3,idUser);
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteByID(int idUser, int idPost) throws SQLException {
        String sql = "DELETE FROM react_post WHERE id_post = ? AND id_account = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,idPost);
            statement.setInt(2,idUser);
            statement.executeUpdate();
        }
    }



}
