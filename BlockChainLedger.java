import java.util.ArrayList;
import java.util.List;

public class BlockChainLedger {
    private List<Block> blockChain;
    private int difficulty;
    private TransactionPool transactionPool;

    public BlockChainLedger(int difficulty) {
        this.blockChain = new ArrayList<>();
        this.difficulty = difficulty;
        this.transactionPool = new TransactionPool();
        createGenesisBlock();
    }

    private void createGenesisBlock() {
        List<Transaction> genesisTransactions = new ArrayList<>();
        Block genesisBlock = new Block("0", genesisTransactions, 0);
        genesisBlock.mineBlock(difficulty);
        blockChain.add(genesisBlock);
    }

    public void addTransaction(Transaction transaction) {
        if (transaction.verifyTransaction()) {
            transactionPool.addTransaction(transaction);
        }
    }

    public Block minePendingTransactions() {
        List<Transaction> validTransactions = transactionPool.getValidTransactions();
        Block newBlock = new Block(getLatestBlock().getBlockHash(), validTransactions, blockChain.size());
        newBlock.mineBlock(difficulty);
        blockChain.add(newBlock);
        transactionPool.clearTransactions();
        return newBlock;
    }

    public Block getLatestBlock() {
        return blockChain.get(blockChain.size() - 1);
    }

    public boolean isChainValid() {
        return ChainValidator.validateChain(blockChain, difficulty);
    }

    public List<Block> getBlockChain() {
        return blockChain;
    }
}
