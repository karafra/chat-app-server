package karafra.chatapp.services;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import karafra.chatapp.domain.chat.Member;
import karafra.chatapp.domain.chat.Message;
import karafra.chatapp.utils.MemberComparatorImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PublicChatServiceImplTest {

    PublicChatServiceImpl publicChatService;

    @BeforeEach
    void init() {
        publicChatService = new PublicChatServiceImpl(new MemberComparatorImpl(), new ArrayList<>(Arrays.asList(new Member("session", "user"))));
    }

    @Test
    void addMember() {
        List<Member> members = publicChatService.addMember(new Member("session2", "user2"));

        assertThat(members).isNotNull();
        assertThat(members).anyMatch(member -> Objects.equals(member.getSessionId(), "session2"));
    }

    @Test
    void getMembers() {
        List<Member> members = publicChatService.getMembers();

        assertThat(members).isNotNull();
        assertThat(members.size()).isEqualTo(1);
    }

    @Test
    void removeMember() {
        List<Member> members = publicChatService.removeMember("session");

        assertThat(members).isNotNull();
        assertThat(members.size()).isEqualTo(0);
    }

    @Test
    void processMessage() {
        Message message = publicChatService.processMessage(new Message(), "session");

        assertThat(message).isNotNull();
        assertThat(message.getAuthorUsername()).isEqualTo("user");
    }
}
