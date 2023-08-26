package Repository;

import Model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends GenericRepository<User>{
    public UserRepository(Connection connection){
        super(connection);
    }
    @Override
    public void insert(User toInsert) throws SQLException {
        String sql = "INSERT INTO account(first_name,last_name,birthday,gender,profile_picture,nickname,email,password) VALUES (?,?,?,?,?,?,?,?)";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            Extract(statement,toInsert);
            statement.executeUpdate();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> AllUsers = new ArrayList<>();
        String sql = "SELECT * FROM user_info";
        try {
            ResultSet resultSet;
            try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    User users = extractUserFromResultSet(resultSet);
                    AllUsers.add(users);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return AllUsers;
    }

    @Override
    public void updateById(int id, User object) throws SQLException {
        String sql = "UPDATE account SET first_name = ?, last_name = ?, profile_picture = ?, nickname = ? , password = ? WHERE id = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setString(1,object.getFirstName());
            statement.setString(2,object.getLastName());
            statement.setString(3,object.getProfilePicture());
            statement.setString(4,object.getNickname());
            statement.setString(5,object.getPassword());
            statement.setInt(6,id);
            statement.executeUpdate();
        }
    }

    private void Extract(@NotNull PreparedStatement statement, User object) throws SQLException {
        statement.setString(1,object.getFirstName());
        statement.setString(2,object.getLastName());
        statement.setDate(3, Date.valueOf(object.getBirthday()));
        statement.setString(4, String.valueOf(object.getGender()));
        statement.setString(5,object.getProfilePicture());
        statement.setString(6,object.getNickname());
        statement.setString(7,object.getEmail());
        statement.setString(8,object.getPassword());
    }


    @Override
    public Optional<User> findById(int id) throws SQLException {
        String sql = "SELECT * FROM user_info WHERE id = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,id);
            try {
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    return Optional.of(extractUserFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM account WHERE id = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }
    }

    @org.jetbrains.annotations.NotNull
    private User extractUserFromResultSet(@NotNull ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String nickname = resultSet.getString("nickname");
        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
        char gender = resultSet.getString("gender").charAt(0);
        String profilePicture = resultSet.getString("profile_picture");
        return new User(id, firstName, lastName, nickname, birthday, gender, profilePicture);
    }
}

