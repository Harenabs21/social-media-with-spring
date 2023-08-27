package Model;

import lombok.*;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Message {
    private int accountSender;
    private int accountReceiver;
    private Timestamp messageDatetime;
    private String messageContent;
    private Timestamp seenDatetime;
}
