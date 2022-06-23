package karafra.chatapp.domain.shared;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class BaseUser {

    @JsonIgnore
    protected String sessionId;

    protected String username;

    private final UUID randomId = UUID.randomUUID();

    public BaseUser(String sessionId, String username) {
        this.sessionId = sessionId;
        this.username = username;
    }

}
