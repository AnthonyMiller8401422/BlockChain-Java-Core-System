import java.util.Map;
import java.util.Random;

public class PosConsensus {
    private Map<String, Double> stakeMap;
    private Random random;

    public PosConsensus(Map<String, Double> stakeMap) {
        this.stakeMap = stakeMap;
        this.random = new Random();
    }

    public String selectForger() {
        double totalStake = stakeMap.values().stream().mapToDouble(Double::doubleValue).sum();
        double randomValue = random.nextDouble() * totalStake;
        double current = 0;
        for (Map.Entry<String, Double> entry : stakeMap.entrySet()) {
            current += entry.getValue();
            if (current >= randomValue) {
                LogUtil.info("PoS选中记账节点：" + entry.getKey() + "，质押量：" + entry.getValue());
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean validateBlock(Block block) {
        return block.getBlockHash().equals(block.calculateBlockHash());
    }
}
