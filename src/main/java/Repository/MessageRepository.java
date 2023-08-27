package Repository;

import Model.Message;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class MessageRepository extends MessageAbstract{

    public MessageRepository(Connection connection){ super(connection);}



    @Override
    public List<Message> AllMessageBetweenTwoUsers(int sender, int receiver) throws SQLException{
        List<Message> AllMessagesBetweenUser = new ArrayList<>();
        String sql = """
                SELECT * from message WHERE
                (id_account_sender = ? AND id_account_receiver = ?)
                OR
                (id_account_sender = ? AND id_account_receiver = ?)
                """;
        try(PreparedStatement  statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,sender);
            statement.setInt(2,receiver);
            statement.setInt(3,receiver);
            statement.setInt(4,sender);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int senderId = resultSet.getInt("id_account_sender");
                int receiverId = resultSet.getInt("id_account_receiver");
                Timestamp messageDatetime = resultSet.getTimestamp("message_datetime");
                String messageContent = resultSet.getString("message_content");
                Timestamp seenDatetime = resultSet.getTimestamp("seen_datetime");
                Message messages = new Message(senderId,receiverId,messageDatetime,messageContent,seenDatetime);
                AllMessagesBetweenUser.add(messages);
            }
        }
        return AllMessagesBetweenUser;
    }

    @Override
    public void insertNewMessage(Message message) throws SQLException{
        String sql = "INSERT INTO message(id_account_sender,id_account_receiver,message_content) VALUES (?,?,?)";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,message.getAccountSender());
            statement.setInt(2,message.getAccountReceiver());
            statement.setString(3,message.getMessageContent());
            statement.executeUpdate();
        }

    }

    @Override
    public void updateById(int sender, int receiver) throws SQLException {
        String sql = """
                UPDATE message
                SET seen_datetime = CURRENT_TIMESTAMP
                WHERE (id_account_sender = ? AND id_account_receiver = ?)
                      OR (id_account_sender = ? AND id_account_receiver = ?)
                """;
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,sender);
            statement.setInt(2,receiver);
            statement.setInt(3,receiver);
            statement.setInt(4,sender);
            statement.executeUpdate();
        }
    }
    @Override
    public void deleteMessageById(int userId) throws SQLException {
        String sql = "DELETE FROM message WHERE id_account_sender = ? OR id_account_receiver = ?";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,userId);
            statement.setInt(2,userId);
            statement.executeUpdate();
        }
    }
}