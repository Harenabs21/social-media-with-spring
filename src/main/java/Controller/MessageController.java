package Controller;

import Model.Message;
import Service.MessageService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Getter
@RestController
@RequestMapping(path = "/message")
public class MessageController {
    private MessageService messageService;
    @GetMapping("/between/")
    public List<Message> displayMessages(@RequestParam int idSender,@RequestParam int idReceiver) throws SQLException {
     return messageService.displayMessagesBetweenTwoUser(idSender,idReceiver);
    }
    @PostMapping("/new")
    public void sendNewMessage(@RequestBody Message message) throws SQLException {
        messageService.sendMessage(message);
    }
    @PutMapping("/seen/")
    public void changeSeenDatetime(@RequestParam int idSender,@RequestParam  int idReceiver) throws SQLException {
        messageService.UpdateSeenDatetime(idSender, idReceiver);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable int id) throws SQLException {
        messageService.deleteMessage(id);
    }
}
