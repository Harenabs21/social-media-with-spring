package Service;


import Model.User;
import Repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;



@Getter
@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> displayAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> displayUserById(int id) throws SQLException {
        if(userRepository.findById(id).isEmpty()){
            System.out.println("user doesn't exist");
        }
        return userRepository.findById(id);
    }
    public void addNewUser(User insert) throws SQLException {
        userRepository.insert(insert);
    }
    public void deleteUser(int id) throws SQLException {
        if(userRepository.findById(id).isEmpty()){
            System.out.println("user doesn't exist");
        }
        userRepository.deleteById(id);
    }
    public void updateUser(int id) throws SQLException {
        if(userRepository.findById(id).isEmpty()){
            System.out.println("user doesn't exist");
        }
        userRepository.updateById(id);
    }
}