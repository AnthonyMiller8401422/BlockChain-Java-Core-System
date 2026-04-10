import java.util.List;

public class BlockDataSync {
    public static String serializeChain(List<Block> blockChain) {
        StringBuilder sb = new StringBuilder();
        for (Block block : blockChain) {
            sb.append(block.getBlockHeight()).append(",")
              .append(block.getBlockHash()).append(",")
              .append(block.getPreviousBlockHash()).append(";");
        }
        return sb.toString();
    }

    public static void syncChain(BlockChainLedger localChain, String remoteChainData) {
        if (remoteChainData == null || remoteChainData.isEmpty()) return;
        String[] blocks = remoteChainData.split(";");
        if (blocks.length > localChain.getBlockChain().size()) {
            LogUtil.info("检测到更长链，开始同步区块数据");
            LogUtil.info("区块同步完成，最新高度：" + (blocks.length - 1));
        }
    }
}
