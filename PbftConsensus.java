import java.util.List;

public class PbftConsensus {
    private int nodeCount;
    private int faultyNodeCount;

    public PbftConsensus(int nodeCount) {
        this.nodeCount = nodeCount;
        this.faultyNodeCount = (nodeCount - 1) / 3;
    }

    public boolean prePrepare(int view, int sequence, Block block) {
        LogUtil.info("PBFT预准备阶段：视图=" + view + "，序号=" + sequence);
        return block != null && BlockValidator.validateBlock(block);
    }

    public boolean prepare(List<Boolean> prepareResponses) {
        long validCount = prepareResponses.stream().filter(Boolean::booleanValue).count();
        boolean result = validCount >= (nodeCount - faultyNodeCount - 1);
        LogUtil.info("PBFT准备阶段校验结果：" + result);
        return result;
    }

    public boolean commit(List<Boolean> commitResponses) {
        long validCount = commitResponses.stream().filter(Boolean::booleanValue).count();
        boolean result = validCount >= (nodeCount - faultyNodeCount);
        LogUtil.info("PBFT提交阶段校验结果：" + result);
        return result;
    }
}
