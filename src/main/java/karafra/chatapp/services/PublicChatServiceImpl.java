package karafra.chatapp.services;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import karafra.chatapp.domain.chat.Member;
import karafra.chatapp.domain.chat.Message;
import karafra.chatapp.domain.chat.MessageType;
import karafra.chatapp.services.declarations.PublicChatService;
import karafra.chatapp.utils.MemberComparator;

@Service
public class PublicChatServiceImpl implements PublicChatService {

    private final List<Member> members;

    private final MemberComparator memberComparator;

    public PublicChatServiceImpl(MemberComparator memberComparator, List<Member> members) {
        this.memberComparator = memberComparator;
        this.members = members;
    }

    public List<Member> addMember(Member member) {
        if (member != null) {
            members.add(member);
            members.sort(memberComparator);
        }

        return members;
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public List<Member> removeMember(String sessionId) {
        members.removeIf(e -> Objects.equals(e.getSessionId(), sessionId));
        return this.members;
    }

    @Override
    public Member getMemberBySessionId(String sessionId) {
        return members.stream()
                .filter(e -> Objects.equals(e.getSessionId(), sessionId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Message processMessage(Message message,
                                  String sessionId) {
        Member author = this.getMemberBySessionId(sessionId);
        message.setAuthorUsername(author.getUsername());
        message.setAuthorRandomId(author.getRandomId());
        message.setCreationDate(Instant.now());
        message.setType(MessageType.MESSAGE);
        return message;
    }

}
