package Repository;

import Model.Message;
import lombok.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public abstract class MessageAbstract {
    private Connection connection;
    public abstract List<Message> AllMessageBetweenTwoUsers(int sender, int receiver) throws SQLException;
    public abstract void insertNewMessage(Message message) throws SQLException;
    public abstract void updateById(int sender, int receiver) throws SQLException;
    public abstract void deleteMessageById(int userId) throws SQLException;

}
