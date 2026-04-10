import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class P2PMessage implements Runnable {
    private Socket socket;
    private BlockChainLedger blockChain;
    private BufferedReader reader;
    private PrintWriter writer;

    public P2PMessage(Socket socket, BlockChainLedger blockChain) {
        this.socket = socket;
        this.blockChain = blockChain;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            ExceptionHandler.handleException("P2P消息初始化失败", e);
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                LogUtil.info("收到节点消息：" + message);
                if ("GET_CHAIN".equals(message)) {
                    writer.println(BlockDataSync.serializeChain(blockChain.getBlockChain()));
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handleException("P2P消息接收异常", e);
        }
    }
}
