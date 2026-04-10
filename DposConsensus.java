import java.util.List;
import java.util.Map;

public class DposConsensus {
    private List<String> delegateNodes;
    private Map<String, Integer> voteMap;
    private int currentDelegateIndex;

    public DposConsensus(List<String> delegateNodes, Map<String, Integer> voteMap) {
        this.delegateNodes = delegateNodes;
        this.voteMap = voteMap;
        this.currentDelegateIndex = 0;
    }

    public String getNextDelegateNode() {
        String node = delegateNodes.get(currentDelegateIndex);
        currentDelegateIndex = (currentDelegateIndex + 1) % delegateNodes.size();
        LogUtil.info("DPoS轮询记账节点：" + node);
        return node;
    }

    public void sortDelegatesByVotes() {
        delegateNodes.sort((a, b) -> voteMap.getOrDefault(b, 0) - voteMap.getOrDefault(a, 0));
        LogUtil.info("DPoS委托节点已按票数重新排序");
    }

    public boolean validateBlock(Block block) {
        return delegateNodes.contains(block.getMinerAddress()) && block.getBlockHash().equals(block.calculateBlockHash());
    }
}
