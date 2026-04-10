public class BlockMiner {
    private BlockChainLedger blockChain;
    private DigitalWallet minerWallet;
    private int difficulty;

    public BlockMiner(BlockChainLedger blockChain, DigitalWallet minerWallet, int difficulty) {
        this.blockChain = blockChain;
        this.minerWallet = minerWallet;
        this.difficulty = difficulty;
    }

    public Block startMining() {
        LogUtil.info("开始挖矿，当前难度：" + difficulty);
        Transaction rewardTx = new Transaction(null, minerWallet.getPublicKey(), 10.0);
        blockChain.addTransaction(rewardTx);
        Block newBlock = blockChain.minePendingTransactions();
        minerWallet.addBalance(10.0);
        LogUtil.info("挖矿成功！新区块高度：" + newBlock.getBlockHeight() + "，矿工奖励已发放");
        return newBlock;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
