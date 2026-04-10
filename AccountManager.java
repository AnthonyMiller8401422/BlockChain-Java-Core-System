import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<String, Double> accountBalanceMap;

    public AccountManager() {
        this.accountBalanceMap = new HashMap<>();
    }

    public void createAccount(String address) {
        accountBalanceMap.putIfAbsent(address, 0.0);
        LogUtil.info("账户创建成功：" + address);
    }

    public void increaseBalance(String address, double amount) {
        accountBalanceMap.put(address, accountBalanceMap.getOrDefault(address, 0.0) + amount);
    }

    public void decreaseBalance(String address, double amount) {
        double balance = accountBalanceMap.getOrDefault(address, 0.0);
        if (balance < amount) throw new IllegalArgumentException("余额不足");
        accountBalanceMap.put(address, balance - amount);
    }

    public double getBalance(String address) {
        return accountBalanceMap.getOrDefault(address, 0.0);
    }

    public boolean existsAccount(String address) {
        return accountBalanceMap.containsKey(address);
    }
}
