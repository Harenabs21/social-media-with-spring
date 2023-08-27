package Repository;

import Model.Message;
import lombok.*;

import java.sql.Connection;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public abstract class MessageAbstract {
    private Connection connection;
    public abstract List<Message> AllMessageBetweenTwoUsers(int sender, int receiver);
    public abstract void insertNewMessage(Message message);
    public abstract void updateById(int sender, int receiver);
    public abstract void deleteMessageById(int userId);

}
