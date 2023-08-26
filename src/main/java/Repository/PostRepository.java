package Repository;

import Model.Post;
import Model.User;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@Repository
public class PostRepository extends GenericRepository<Post> {
    private Post posts;
    public PostRepository(Connection connection){
        super(connection);
    }
    @Override
    public void insert(@NotNull Post toInsert) throws SQLException {
        String sql = "INSERT INTO post(id_account,posting_date,posting_time,post_content,post_photo) VALUES(?,?,?,?,?) ";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, toInsert.getId_account().getId());
            statement.setDate(2, Date.valueOf(toInsert.getPostingDate()));
            statement.setTime(3, Time.valueOf(toInsert.getPostingTime()));
            statement.setString(4, toInsert.getPostContent());
            statement.setString(5, toInsert.getPostPhoto());
            statement.executeUpdate();
        }

    }

    @Override
    public List<Post> findAll() throws SQLException {
        List<Post> AllPosts = new ArrayList<>();
        String sql = "SELECT a.nickname, p.posting_date, p.posting_time, p.post_content, p.post_photo FROM post p INNER JOIN account a ON p.id_account = a.id";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Post post = extractPostFromResultSet(resultSet);
                AllPosts.add(post);
            }
        }
        return AllPosts;

    }

    @Override
    public void updateById(int id) throws SQLException {
        String sql = "UPDATE post SET post_content = ?, post_photo = ? WHERE id = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setString(1,posts.getPostContent());
            statement.setString(2,posts.getPostPhoto());
            statement.setInt(3,id);
            statement.executeUpdate();
        }
    }



    @Override
    public Optional<Post> findById(int id) throws SQLException {
        String sql = """
                SELECT a.nickname, p.posting_date, p.posting_time
                , p.post_content, p.post_photo FROM
                post p INNER JOIN account a ON p.id_account = a.id WHERE
                 p.id_account = ? ORDER BY posting_date DESC""";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(extractPostFromResultSet(resultSet));
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM post WHERE id = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
    }

    private Post extractPostFromResultSet(@NotNull ResultSet resultSet) throws SQLException{
        int postId = resultSet.getInt("id");
        LocalDate postingDate = resultSet.getDate("posting_date").toLocalDate();
        LocalTime postingTime = resultSet.getTime("posting_time").toLocalTime();
        String postContent = resultSet.getString("post_content");
        String postPhoto = resultSet.getString("post_photo");
        String nickname = resultSet.getString("nickname");
        User username = new User(nickname);
        return new Post(postId,username,postingDate,postingTime,postContent,postPhoto);
    }
}

