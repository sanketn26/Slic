__author__ = 'slic'

from autobahn.twisted.websocket import WebSocketClientProtocol, \
    WebSocketClientFactory


class WebSocketEchoProtocol(WebSocketClientProtocol):

    def onConnect(self, response):
        print("Server connected: {0}".format(response.peer))

    def onOpen(self):
        print("WebSocket connection open.")

        def hello():
            self.sendMessage(u"Hello, world!".encode('utf8'))
            self.factory.reactor.callLater(1, hello)

        # start sending messages every second ..
        hello()

    def onMessage(self, payload, isBinary):
        if isBinary:
            print("Binary message received: {0} bytes".format(len(payload)))
        else:
            print("Text message received: {0}".format(payload.decode('utf8')))

    def onClose(self, wasClean, code, reason):
        print("WebSocket connection closed: {0}".format(reason))


if __name__ == '__main__':

    import sys

    from twisted.python import log
    from twisted.internet import reactor

    log.startLogging(sys.stdout)

    for i in range(10000):
        factory = WebSocketClientFactory("ws://localhost:8080/echo", debug=False)
        factory.protocol = WebSocketEchoProtocol
        reactor.connectTCP("127.0.0.1", 8080, factory)

    reactor.run()
