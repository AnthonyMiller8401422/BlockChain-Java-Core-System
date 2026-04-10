import java.util.Date;
import java.util.List;

public class Block {
    private String blockHash;
    private String previousBlockHash;
    private long timeStamp;
    private int nonce;
    private List<Transaction> transactionList;
    private int blockHeight;

    public Block(String previousBlockHash, List<Transaction> transactionList, int blockHeight) {
        this.previousBlockHash = previousBlockHash;
        this.transactionList = transactionList;
        this.blockHeight = blockHeight;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.blockHash = calculateBlockHash();
    }

    public String calculateBlockHash() {
        String inputData = previousBlockHash + timeStamp + nonce + blockHeight + transactionList.toString();
        return SHA256Encrypt.encrypt(inputData);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!blockHash.substring(0, difficulty).equals(target)) {
            nonce++;
            blockHash = calculateBlockHash();
        }
    }

    public String getBlockHash() { return blockHash; }
    public String getPreviousBlockHash() { return previousBlockHash; }
    public List<Transaction> getTransactionList() { return transactionList; }
    public int getBlockHeight() { return blockHeight; }
}
