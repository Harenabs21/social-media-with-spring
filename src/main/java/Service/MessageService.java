package Service;

import Model.Message;
import Repository.MessageRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@AllArgsConstructor
@Getter
@Service
public class MessageService {
    private MessageRepository messageRepository;
    public List<Message> displayMessagesBetweenTwoUser(int sender, int receiver) throws SQLException {
        if(sender == receiver){
            System.out.println("the id cannot be the same");
        }
        return messageRepository.AllMessageBetweenTwoUsers(sender,receiver);
    }
    public void sendMessage(Message insert) throws SQLException {
        if(insert.getAccountSender() == insert.getAccountReceiver()){
            System.out.println("the id cannot be the same");
        }
        messageRepository.insertNewMessage(insert);
    }
   public void UpdateSeenDatetime(int sender, int receiver) throws SQLException {
       if(sender == receiver){
           System.out.println("the id cannot be the same");
       }
       messageRepository.updateById(sender,receiver);
   }
   public void deleteMessage(int idUser) throws SQLException{

       messageRepository.deleteMessageById(idUser);
   }
}
