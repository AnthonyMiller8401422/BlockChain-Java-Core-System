import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class DigitalWallet {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private String walletAddress;
    private double balance;

    public DigitalWallet() throws Exception {
        KeyPair keyPair = RSAEncrypt.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
        this.walletAddress = generateWalletAddress();
        this.balance = 0.0;
        WalletKeyStore.saveWallet(this);
    }

    private String generateWalletAddress() {
        String pubKeyStr = publicKey.toString();
        return SHA256Encrypt.encrypt(pubKeyStr).substring(0, 42);
    }

    public Transaction createTransaction(PublicKey receiver, double amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("钱包余额不足");
        }
        Transaction transaction = new Transaction(publicKey, receiver, amount);
        try {
            transaction.signTransaction(privateKey);
            balance -= amount;
            return transaction;
        } catch (Exception e) {
            ExceptionHandler.handleException("交易签名失败", e);
            return null;
        }
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public String getWalletAddress() { return walletAddress; }
    public double getBalance() { return balance; }
    public PublicKey getPublicKey() { return publicKey; }
}
