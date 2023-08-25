package Service;

import Model.Message;
import Repository.MessageRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class MessageService {
    private MessageRepository messageRepository;
    public List<Message> displayMessagesBetweenTwoUser(Message sender, Message receiver) throws SQLException {
        if(sender.getAccountSender().getId() == receiver.getAccountReceiver().getId()){
            System.out.println("the id cannot be the same");
        }
        return messageRepository.findAllMessagesBetweenUser(sender,receiver);
    }
    public void sendMessage(Message insert) throws SQLException {
        if(insert.getAccountSender().getId() == insert.getAccountReceiver().getId()){
            System.out.println("the id cannot be the same");
        }
        messageRepository.insertNewContent(insert);
    }
   public void UpdateSeenDatetime(Message message) throws SQLException {
       if(message.getAccountSender().getId() == message.getAccountReceiver().getId()){
           System.out.println("the id cannot be the same");
       }
       messageRepository.updateByID(message);
   }
   public void deleteMessage(Message message) throws SQLException{

       if(message.getAccountSender().getId() == message.getAccountReceiver().getId()){
           System.out.println("the id cannot be the same");
       }
       messageRepository.deleteByID(message);
   }
}
