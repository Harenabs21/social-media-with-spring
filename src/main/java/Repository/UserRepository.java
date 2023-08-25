package Repository;

import Model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class UserRepository extends GenericRepository<User> {
    private User myUser;
    public UserRepository(Connection connection){
        super(connection);
    }
    @Override
    public void insert(User toInsert) throws SQLException {
        String sql = "INSERT INTO account(first_name,last_name,birthday,gender,profile_picture,nickname,email,password) VALUES (?,?,?,?,?,?,?,?)";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            Extract(statement);
            statement.executeUpdate();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> AllUsers = new ArrayList<>();
        String sql = "SELECT id, nickname, first_name, last_name, birthday, gender, profile_picture FROM account";
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
    public void updateById(int id) throws SQLException {
        String sql = "UPDATE account SET first_name = ?, last_name = ?, birthday = ?, gender = ?, profile_picture = ?, nickname = ? ,email = ?, password = ? WHERE id = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            Extract(statement);
            statement.setInt(9,id);
            statement.executeUpdate();
        }
    }

    private void Extract( PreparedStatement statement) throws SQLException {
        statement.setString(1,myUser.getFirstName());
        statement.setString(2,myUser.getLastName());
        statement.setDate(3, Date.valueOf(myUser.getBirthday()));
        statement.setString(4, String.valueOf(myUser.getGender()));
        statement.setString(5,myUser.getProfilePicture());
        statement.setString(6,myUser.getNickname());
        statement.setString(7,myUser.getEmail());
        statement.setString(8,myUser.getPassword());
    }


    @Override
    public Optional<User> findById(int id) throws SQLException {
        String sql = "SELECT id, nickname, first_name, last_name, birthday, gender, profile_picture FROM account WHERE id = ?";
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

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String nickname = resultSet.getString("nickname");
        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
        char gender = resultSet.getString("gender").charAt(0);
        String profilePicture = resultSet.getString("profile_picture");
        return new User(id, firstName, lastName, nickname, birthday, gender, profilePicture);
    }

    public static void main(String[] args) {
        UserRepository userRepository =new UserRepository();
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            System.out.println(user);
        }
    }
}

