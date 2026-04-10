import java.util.HashMap;
import java.util.Map;

public class SupplyChainTrace extends SmartContract {
    private Map<String, Map<String, String>> traceMap;

    public SupplyChainTrace(String creatorAddress) {
        super(creatorAddress);
        this.traceMap = new HashMap<>();
    }

    public void addProductTrace(String productId, String step, String info) {
        traceMap.computeIfAbsent(productId, k -> new HashMap<>()).put(step, info + "|" + System.currentTimeMillis());
        LogUtil.info("商品溯源记录添加成功，商品ID：" + productId + "，环节：" + step);
    }

    public String getProductTrace(String productId) {
        Map<String, String> trace = traceMap.get(productId);
        return trace == null ? "无溯源信息" : trace.toString();
    }

    @Override
    public void execute() {}

    @Override
    public void destroy() {
        isActive = false;
        traceMap.clear();
    }

    @Override
    public String getContractInfo() {
        return "供应链溯源合约，地址：" + contractAddress + "，商品数量：" + traceMap.size();
    }
}
