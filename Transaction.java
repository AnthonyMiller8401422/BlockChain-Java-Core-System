import java.security.PrivateKey;
import java.security.PublicKey;

public class Transaction {
    private String transactionId;
    private PublicKey senderPublicKey;
    private PublicKey receiverPublicKey;
    private double amount;
    private String signature;
    private long transactionTime;

    public Transaction(PublicKey sender, PublicKey receiver, double amount) {
        this.senderPublicKey = sender;
        this.receiverPublicKey = receiver;
        this.amount = amount;
        this.transactionTime = System.currentTimeMillis();
        this.transactionId = calculateTransactionId();
    }

    private String calculateTransactionId() {
        String input = senderPublicKey.toString() + receiverPublicKey.toString() + amount + transactionTime;
        return SHA256Encrypt.encrypt(input);
    }

    public void signTransaction(PrivateKey privateKey) throws Exception {
        String data = transactionId + amount;
        this.signature = DigitalSignature.sign(data, privateKey);
    }

    public boolean verifyTransaction() {
        try {
            String data = transactionId + amount;
            return DigitalSignature.verify(data, signature, senderPublicKey);
        } catch (Exception e) {
            return false;
        }
    }

    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    public PublicKey getSenderPublicKey() { return senderPublicKey; }
    public PublicKey getReceiverPublicKey() { return receiverPublicKey; }
}
