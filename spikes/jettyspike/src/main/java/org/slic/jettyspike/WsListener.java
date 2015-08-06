package org.slic.jettyspike;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

import java.io.IOException;

/**
 * Created by slic on 5/8/15.
 */
public class WsListener implements WebSocketListener {

    public void onWebSocketBinary(byte[] bytes, int i, int i1) {
    }

    public void onWebSocketClose(int i, String s) {
    }

    public void onWebSocketConnect(Session session) {
        SessionStore.getInstance().addSession(session);
        try {
            session.getRemote().sendString("You are connected...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onWebSocketError(Throwable throwable) {

    }

    public void onWebSocketText(String s) {
    }
}
