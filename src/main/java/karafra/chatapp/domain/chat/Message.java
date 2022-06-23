package karafra.chatapp.domain.chat;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;

import karafra.chatapp.domain.shared.BaseMessage;
import karafra.chatapp.utils.CustomInstantDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Message extends BaseMessage {

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    @JsonSerialize(using = InstantSerializer.class)
    private Instant creationDate;

    private UUID authorRandomId;

    private String authorUsername;

    private MessageType type;

    public Message(String message, Instant creationDate, UUID authorRandomId, String authorUsername, MessageType type) {
        super(message);
        this.creationDate = creationDate;
        this.authorRandomId = authorRandomId;
        this.authorUsername = authorUsername;
        this.type = type;
    }
}
