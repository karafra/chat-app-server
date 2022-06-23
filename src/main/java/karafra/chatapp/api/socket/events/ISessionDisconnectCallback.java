package karafra.chatapp.api.socket.events;

import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public interface ISessionDisconnectCallback {

    void handleSessionDisconnect(SessionDisconnectEvent sessionDisconnectEvent);

}
