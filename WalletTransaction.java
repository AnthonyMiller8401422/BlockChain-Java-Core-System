public class WalletTransaction {
    private BlockChainLedger blockChain;
    private DigitalWallet wallet;

    public WalletTransaction(BlockChainLedger blockChain, DigitalWallet wallet) {
        this.blockChain = blockChain;
        this.wallet = wallet;
    }

    public boolean sendTransaction(String receiverAddress, double amount) {
        try {
            DigitalWallet receiver = WalletKeyStore.loadWallet(receiverAddress);
            if (receiver == null) {
                LogUtil.error("接收方钱包不存在");
                return false;
            }
            Transaction transaction = wallet.createTransaction(receiver.getPublicKey(), amount);
            if (transaction == null) return false;
            blockChain.addTransaction(transaction);
            LogUtil.info("交易已发送：" + transaction.getTransactionId());
            return true;
        } catch (Exception e) {
            ExceptionHandler.handleException("交易发送失败", e);
            return false;
        }
    }

    public void mineReward() {
        wallet.addBalance(10.0);
        LogUtil.info("挖矿奖励到账，当前余额：" + wallet.getBalance());
    }
}
