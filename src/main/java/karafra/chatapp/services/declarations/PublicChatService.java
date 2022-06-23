package karafra.chatapp.services.declarations;

import java.util.List;

import karafra.chatapp.domain.chat.Member;
import karafra.chatapp.domain.chat.Message;

public interface PublicChatService {

    List<Member> addMember(Member member);

    List<Member> getMembers();

    List<Member> removeMember(String sessionId);

    Member getMemberBySessionId(String sessionId);

    Message processMessage(Message message, String sessionId);
}
