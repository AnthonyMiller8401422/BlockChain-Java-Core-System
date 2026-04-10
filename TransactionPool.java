import java.util.ArrayList;
import java.util.List;

public class TransactionPool {
    private List<Transaction> pendingTransactions;

    public TransactionPool() {
        this.pendingTransactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        if (transaction == null || !transaction.verifyTransaction()) {
            throw new IllegalArgumentException("无效交易，拒绝加入交易池");
        }
        pendingTransactions.add(transaction);
        LogUtil.info("交易加入交易池：" + transaction.getTransactionId());
    }

    public List<Transaction> getValidTransactions() {
        List<Transaction> validList = new ArrayList<>();
        for (Transaction tx : pendingTransactions) {
            if (tx.verifyTransaction()) {
                validList.add(tx);
            }
        }
        return validList;
    }

    public void clearTransactions() {
        pendingTransactions.clear();
        LogUtil.info("交易池已清空");
    }

    public int getPoolSize() {
        return pendingTransactions.size();
    }
}
