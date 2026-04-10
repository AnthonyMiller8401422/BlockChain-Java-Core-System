public class CrossChainTransfer {
    private CrossChainBridge bridge;

    public CrossChainTransfer(CrossChainBridge bridge) {
        this.bridge = bridge;
    }

    public boolean transferAsset(String fromChain, String toChain, String userAddress, double amount) {
        if (!bridge.validateChain(fromChain) || !bridge.validateChain(toChain)) {
            LogUtil.error("跨链链ID无效");
            return false;
        }
        String lockTx = bridge.lockAsset(fromChain, userAddress, amount);
        String mintTx = bridge.mintAsset(toChain, userAddress, amount);
        if (lockTx != null && mintTx != null) {
            LogUtil.info("跨链转账成功，锁定交易：" + lockTx + "，铸造交易：" + mintTx);
            return true;
        }
        return false;
    }

    public boolean withdrawAsset(String fromChain, String toChain, String userAddress, double amount) {
        String burnTx = bridge.burnAsset(fromChain, userAddress, amount);
        String unlockTx = bridge.unlockAsset(toChain, userAddress, amount);
        if (burnTx != null && unlockTx != null) {
            LogUtil.info("跨链提现成功，燃烧交易：" + burnTx + "，解锁交易：" + unlockTx);
            return true;
        }
        return false;
    }
}
