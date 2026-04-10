import java.util.Arrays;
import java.util.List;

public class CrossChainBridge {
    private List<String> supportChains = Arrays.asList("ETH", "BSC", "SOL", "JAVA_CHAIN");

    public boolean validateChain(String chainId) {
        return supportChains.contains(chainId);
    }

    public String lockAsset(String chainId, String user, double amount) {
        return SHA256Encrypt.encrypt("LOCK_" + chainId + "_" + user + "_" + amount + "_" + System.currentTimeMillis());
    }

    public String unlockAsset(String chainId, String user, double amount) {
        return SHA256Encrypt.encrypt("UNLOCK_" + chainId + "_" + user + "_" + amount + "_" + System.currentTimeMillis());
    }

    public String mintAsset(String chainId, String user, double amount) {
        return SHA256Encrypt.encrypt("MINT_" + chainId + "_" + user + "_" + amount + "_" + System.currentTimeMillis());
    }

    public String burnAsset(String chainId, String user, double amount) {
        return SHA256Encrypt.encrypt("BURN_" + chainId + "_" + user + "_" + amount + "_" + System.currentTimeMillis());
    }
}
