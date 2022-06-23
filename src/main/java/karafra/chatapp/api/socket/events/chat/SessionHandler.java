package karafra.chatapp.api.socket.events.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import karafra.chatapp.api.socket.events.ISessionDisconnectCallback;

public abstract class SessionHandler implements ISessionDisconnectCallback {

    protected SimpMessageSendingOperations sendingOperations;

    @Autowired
    public SessionHandler(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }

}
