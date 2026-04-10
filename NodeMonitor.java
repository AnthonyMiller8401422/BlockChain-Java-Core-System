public class NodeMonitor implements Runnable {
    private BlockChainLedger blockChain;
    private P2PNode p2pNode;
    private boolean isRunning;

    public NodeMonitor(BlockChainLedger blockChain, P2PNode p2pNode) {
        this.blockChain = blockChain;
        this.p2pNode = p2pNode;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                LogUtil.info("=== 节点监控报告 ===");
                LogUtil.info("区块链高度：" + blockChain.getBlockChain().size());
                LogUtil.info("链有效性：" + blockChain.isChainValid());
                LogUtil.info("====================");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                ExceptionHandler.handleException("节点监控异常", e);
                isRunning = false;
            }
        }
    }

    public void stopMonitor() {
        this.isRunning = false;
    }
}
