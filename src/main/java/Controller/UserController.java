package Controller;

import Model.User;
import Service.UserService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
@RestController
@RequestMapping(path = "/users")
public class UserController {
    private UserService userService;
    @GetMapping
    public List<User> findAllUsers(){
        return userService.displayAllUsers();
    }
    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable int id) throws SQLException {
        return userService.displayUserById(id);
    }
    @PostMapping
    public void newUser(@RequestBody User user) throws SQLException {
        userService.addNewUser(user);
    }
    @PutMapping("/{id}")
    public void updateUserById(@PathVariable int id, @RequestBody User user) throws SQLException {
        userService.updateUser(id,user);
    }
    @DeleteMapping ("/{id}")
    public void deleteUserById(@PathVariable int id) throws SQLException {
        userService.deleteUser(id);
    }
}
