import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class P2PNode implements Runnable {
    private int port;
    private List<Socket> connectedNodes;
    private BlockChainLedger blockChain;

    public P2PNode(int port, BlockChainLedger blockChain) {
        this.port = port;
        this.blockChain = blockChain;
        this.connectedNodes = new ArrayList<>();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LogUtil.info("P2P节点启动，端口：" + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                connectedNodes.add(clientSocket);
                new Thread(new P2PMessage(clientSocket, blockChain)).start();
                LogUtil.info("新节点连接成功，当前连接数：" + connectedNodes.size());
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("P2P节点运行异常", e);
        }
    }

    public void connectToNode(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            connectedNodes.add(socket);
            new Thread(new P2PMessage(socket, blockChain)).start();
            LogUtil.info("成功连接远程节点：" + host + ":" + port);
        } catch (Exception e) {
            ExceptionHandler.handleException("连接远程节点失败", e);
        }
    }
}
