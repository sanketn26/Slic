package org.slic.spikes.vertxspike;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.ServerWebSocket;
import org.vertx.java.platform.Verticle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.WeakHashMap;

/**
 * Created by slic on 4/8/15.
 */
public class WebsocketMessenger extends Verticle {

    private List<ServerWebSocket> socketMap;

    public WebsocketMessenger(){
        socketMap = new ArrayList<>();
    }

    @Override
    public void start(){

        final HttpServer server = vertx.createHttpServer();
        final Random randomGenerator = new Random();
        server.websocketHandler(new Handler<ServerWebSocket>() {
            @Override
            public void handle(ServerWebSocket serverWebSocket) {
                socketMap.add(serverWebSocket);
            }
        });
        vertx.setPeriodic(5 * 1000, new Handler<Long>() {
            @Override
            public void handle(Long aLong) {
                int socketIndex = randomGenerator.nextInt(socketMap.size());
                ServerWebSocket socket = socketMap.get(socketIndex);
                socket.writeTextFrame("Hello....");
            }
        });


        server.listen(8080);
    }

}
