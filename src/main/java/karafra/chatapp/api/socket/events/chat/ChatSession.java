package karafra.chatapp.api.socket.events.chat;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import karafra.chatapp.services.declarations.PublicChatService;

@Component
public class ChatSession extends SessionHandler {

    private PublicChatService chatService;

    public ChatSession(SimpMessageSendingOperations sendingOperations,
                                    PublicChatService publicChatService) {
        super(sendingOperations);
        this.chatService = publicChatService;
    }

    @Override
    public void handleSessionDisconnect(SessionDisconnectEvent sessionDisconnectEvent) {
        chatService.removeMember(sessionDisconnectEvent.getSessionId());
        sendingOperations.convertAndSend("/queue/public/chat/users", chatService.getMembers());
    }

}
