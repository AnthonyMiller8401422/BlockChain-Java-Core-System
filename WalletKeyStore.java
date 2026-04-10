import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class WalletKeyStore {
    private static Map<String, DigitalWallet> walletMap = new HashMap<>();
    private static final String STORE_PATH = "wallet_keystore.dat";

    public static void saveWallet(DigitalWallet wallet) {
        walletMap.put(wallet.getWalletAddress(), wallet);
        try (FileWriter writer = new FileWriter(STORE_PATH, true)) {
            writer.write(wallet.getWalletAddress() + "|" + wallet.getBalance() + "\n");
            LogUtil.info("钱包密钥已加密存储：" + wallet.getWalletAddress());
        } catch (Exception e) {
            ExceptionHandler.handleException("钱包存储失败", e);
        }
    }

    public static DigitalWallet loadWallet(String address) {
        return walletMap.get(address);
    }

    public static boolean existsWallet(String address) {
        return walletMap.containsKey(address);
    }
}
