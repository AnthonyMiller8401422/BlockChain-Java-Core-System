import java.util.List;

public class ChainValidator {
    public static boolean validateChain(List<Block> blockChain, int difficulty) {
        if (blockChain.isEmpty()) return true;
        Block genesisBlock = blockChain.get(0);
        if (!genesisBlock.getPreviousBlockHash().equals("0")) return false;

        for (int i = 1; i < blockChain.size(); i++) {
            Block currentBlock = blockChain.get(i);
            Block previousBlock = blockChain.get(i - 1);
            if (!BlockValidator.validateBlock(currentBlock, previousBlock, difficulty)) {
                LogUtil.error("区块链在高度 " + i + " 校验失败");
                return false;
            }
        }
        LogUtil.info("区块链完整性校验通过");
        return true;
    }
}
