package karafra.chatapp.domain.chat;

import karafra.chatapp.domain.shared.BaseUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Member extends BaseUser {

    public Member(String sessionId, String username) {
        super(sessionId, username);
    }

}
