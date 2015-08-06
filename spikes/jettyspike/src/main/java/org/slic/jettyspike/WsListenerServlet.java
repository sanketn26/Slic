package org.slic.jettyspike;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Created by slic on 6/8/15.
 */
public class WsListenerServlet extends WebSocketServlet{

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(1000 * 60 * 60);
        webSocketServletFactory.register(WsListener.class);
    }
}
