package Controller;

import Model.Message;
import Service.MessageService;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Getter
@RestController
@RequestMapping(path = "/message")
public class MessageController {
    private MessageService messageService;
    @GetMapping
    public List<Message> displayMessages() throws SQLException {
//        return messageService.displayMessagesBetweenTwoUser(message);
        return null;
    }
}
