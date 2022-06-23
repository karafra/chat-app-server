package karafra.chatapp.api.socket;

import java.time.Instant;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import karafra.chatapp.api.utils.WebSocketUtils;
import karafra.chatapp.domain.chat.Member;
import karafra.chatapp.domain.chat.Message;
import karafra.chatapp.domain.chat.MessageType;
import karafra.chatapp.services.PublicChatServiceImpl;

@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    private final PublicChatServiceImpl chatService;

    public ChatController(SimpMessageSendingOperations messagingTemplate,
                                PublicChatServiceImpl chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/public/chat/{username}/join")
    @SendTo("/topic/public/chat")
    public Message joinChat(@DestinationVariable String username,
                            @Header("simpSessionId") String sessionId) {
        Member member = new Member(sessionId, username);
        chatService.addMember(member);
        messagingTemplate.convertAndSendToUser(sessionId, "/queue/public/chat/id", member.getRandomId(), WebSocketUtils.getMessageHeaders(sessionId));
        messagingTemplate.convertAndSend("/queue/public/chat/users", chatService.getMembers());
        return new Message(username + " joined chat", Instant.now(), member.getRandomId(), username, MessageType.SYSTEM);
    }

    @MessageMapping("/public/chat/send")
    @SendTo("/topic/public/chat")
    public Message sendMessage(@Payload Message message,
                               @Header("simpSessionId") String sessionId) {
        return chatService.processMessage(message, sessionId);
    }

}
