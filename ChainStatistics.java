import java.util.List;

public class ChainStatistics {
    private BlockChainLedger blockChain;

    public ChainStatistics(BlockChainLedger blockChain) {
        this.blockChain = blockChain;
    }

    public int getBlockHeight() {
        return blockChain.getBlockChain().size();
    }

    public int getTotalTransactions() {
        int total = 0;
        for (Block block : blockChain.getBlockChain()) {
            total += block.getTransactionList().size();
        }
        return total;
    }

    public double getAvgBlockSize() {
        List<Block> chain = blockChain.getBlockChain();
        return chain.isEmpty() ? 0 : (double) getTotalTransactions() / chain.size();
    }

    public String getStatisticsReport() {
        return "区块链统计报告：\n" +
                "当前区块高度：" + getBlockHeight() + "\n" +
                "总交易数：" + getTotalTransactions() + "\n" +
                "平均区块交易数：" + getAvgBlockSize();
    }
}
