package Repository;

import Model.Message;
import Model.User;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Repository
public class MessageRepository extends SpecificRepository<Message>{

    public MessageRepository(Connection connection){ super(connection);}

    public List<Message> findAllMessagesBetweenUser(Message sender,  Message receiver) throws SQLException {
        List<Message> AllMessagesBetweenUser = new ArrayList<>();
        String sql = """
                SELECT
                    a_sender.nickname AS sender_nickname,
                    a_receiver.nickname AS receiver_nickname,
                    m.message_datetime,
                    m.message_content,
                    m.seen_datetime
                FROM
                    message m
                INNER JOIN
                    account a_sender ON m.id_account_sender = a_sender.id
                INNER JOIN
                    account a_receiver ON m.id_account_receiver = a_receiver.id
                WHERE
                    (m.id_account_sender = ? AND m.id_account_receiver = ?)
                    OR
                    (m.id_account_sender = ? AND m.id_account_receiver = ?)
                ORDER BY
                    m.message_datetime DESC;""";
        try(PreparedStatement  statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,sender.getAccountSender().getId());
            statement.setInt(2,receiver.getAccountReceiver().getId());
            statement.setInt(3,sender.getAccountReceiver().getId());
            statement.setInt(4,receiver.getAccountSender().getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String senderNickname = resultSet.getString("sender_nickname");
                String receiverNickname = resultSet.getString("receiver_nickname");
                Timestamp messageDatetime = resultSet.getTimestamp("message_datetime");
                String messageContent = resultSet.getString("message_content");
                Timestamp seenDatetime = resultSet.getTimestamp("seen_datetime");
                User Sender = new User(senderNickname);
                User Receiver = new User(receiverNickname);
                Message messages = new Message(Sender,Receiver,messageDatetime,messageContent,seenDatetime);
                AllMessagesBetweenUser.add(messages);
            }
        }
        return AllMessagesBetweenUser;
    }


    private void ExtractFromUpdateAndDelete(Message message, PreparedStatement statement) throws SQLException {
            statement.setInt(1,message.getAccountSender().getId());
            statement.setInt(2,message.getAccountReceiver().getId());
            statement.setInt(3,message.getAccountReceiver().getId());
            statement.setInt(4,message.getAccountSender().getId());

    }

    @Override
    public void insertNewContent(Message insert) throws SQLException {
        String sql = "INSERT INTO message(id_account_sender,id_account_receiver,message_datetime,message_content, seen_datetime) VALUES (?,?,?,?,?)";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setInt(1,insert.getAccountSender().getId());
            statement.setInt(2,insert.getAccountReceiver().getId());
            statement.setTimestamp(3,insert.getMessageDatetime());
            statement.setString(4,insert.getMessageContent());
            statement.setTimestamp(5,insert.getSeenDatetime());
            statement.executeUpdate();
        }

    }

    @Override
    public void updateByID(Message object) throws SQLException {
        String sql = """
                UPDATE message
                SET seen_datetime = CURRENT_TIMESTAMP
                WHERE (id_account_sender = ? AND id_account_receiver = ?)
                      OR (id_account_sender = ? AND id_account_receiver = ?);
                """;
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            ExtractFromUpdateAndDelete(object,statement);
            statement.executeUpdate();
        }

    }

    @Override
    public void deleteByID(Message object) throws SQLException {
        String sql = "DELETE FROM message WHERE (id_account_sender = ? AND id_account_receiver = ?) OR (id_account_sender = ? AND id_account_receiver = ?)";
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            ExtractFromUpdateAndDelete(object,statement);
            statement.executeUpdate();
        }
    }
}
