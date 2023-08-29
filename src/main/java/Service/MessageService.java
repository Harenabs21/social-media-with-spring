package Service;

import Model.Message;
import Model.User;
import Repository.MessageRepository;
import Repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Getter
@Service
public class MessageService {
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    public List<Message> displayMessagesBetweenTwoUser(int sender, int receiver) throws SQLException {
        if(sender == receiver){
            throw new IllegalArgumentException("the id cannot be the same");
        }
        return messageRepository.AllMessageBetweenTwoUsers(sender,receiver);
    }
    public void sendMessage(Message insert) throws SQLException {
        if(insert.getAccountSender() == insert.getAccountReceiver()){
            throw new IllegalArgumentException("the id cannot be the same");
        }
        messageRepository.insertNewMessage(insert);
    }
   public void UpdateSeenDatetime(int sender, int receiver) throws SQLException {
       if(sender == receiver){
           throw new IllegalArgumentException("the id cannot be the same");
       }
       messageRepository.updateById(sender,receiver);
   }
   public void deleteMessage(int idUser) throws SQLException{
       Optional<User> existingUserOptional = userRepository.findById(idUser);
       if(existingUserOptional.isEmpty()){
           throw new ResourceNotFoundException("User not found with id"+idUser);
       }
       messageRepository.deleteMessageById(idUser);
   }
}
