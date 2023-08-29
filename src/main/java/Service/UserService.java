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
            throw new ResourceNotFoundException("User not found with id"+id);
        }
        return userRepository.findById(id);
    }
    public void addNewUser(User insert) throws SQLException {
        userRepository.insert(insert);
    }
    public void deleteUser(int id) throws SQLException {
        if(userRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("User not found with id"+id);
        }
        userRepository.deleteById(id);
    }
    public void updateUser(int id, User user) throws SQLException {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if(existingUserOptional.isEmpty()){
            throw new ResourceNotFoundException("User not found with id"+id);
        }
        else{
            User existingUser = existingUserOptional.get();
            if(user.getFirstName() != null){
                existingUser.setFirstName(user.getFirstName());
            }
            if(user.getLastName() != null){
                existingUser.setLastName(user.getLastName());
            }
            if(user.getProfilePicture() != null){
                existingUser.setProfilePicture(user.getProfilePicture());
            }
            if(user.getNickname() != null){
                existingUser.setNickname(user.getNickname());
            }
            userRepository.updateById(id,existingUser);
        }
    }
}
