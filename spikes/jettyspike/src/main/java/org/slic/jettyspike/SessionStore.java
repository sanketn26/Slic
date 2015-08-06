package org.slic.jettyspike;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.*;

/**
 * Created by slic on 5/8/15.
 */
public class SessionStore {

    private final List<Session> internalSessionStore;
    private static final Object lockObj = new Object();
    private final Timer internalTimer;
    private final Random randomGenerator;
    private static SessionStore self;

    private SessionStore(){
        internalSessionStore = new ArrayList<Session>();
        randomGenerator = new Random();
        internalTimer = new Timer();
        internalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int socketIndex = randomGenerator.nextInt(internalSessionStore.size());
                Session socket = internalSessionStore.get(socketIndex);
                try {
                    socket.getRemote().sendString("Hello.....");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, 1000 * 10, 1000 * 5);
    }

    public static SessionStore getInstance(){
        if(self == null){
            synchronized (lockObj){
                if(self == null){
                    self = new SessionStore();
                }
            }
        }
        return self;
    }

    public void addSession(Session sessionInstance){
        internalSessionStore.add(sessionInstance);
    }

}
