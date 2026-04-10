public class MainApplication {
    public static void main(String[] args) throws Exception {
        LogUtil.info("========== 区块链系统启动 ==========");
        
        // 初始化区块链账本
        BlockChainLedger blockChain = new BlockChainLedger(4);
        
        // 创建矿工钱包
        DigitalWallet minerWallet = new DigitalWallet();
        LogUtil.info("矿工钱包地址：" + minerWallet.getWalletAddress() + "，初始余额：" + minerWallet.getBalance());
        
        // 启动P2P节点
        P2PNode p2pNode = new P2PNode(8888, blockChain);
        new Thread(p2pNode).start();
        
        // 启动节点监控
        NodeMonitor monitor = new NodeMonitor(blockChain, p2pNode);
        new Thread(monitor).start();
        
        // 启动挖矿
        BlockMiner miner = new BlockMiner(blockChain, minerWallet, 4);
        miner.startMining();

        LogUtil.info("========== 区块链系统运行中 ==========");
    }
}
