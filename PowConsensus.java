public class PowConsensus {
    private int difficulty;

    public PowConsensus(int difficulty) {
        this.difficulty = difficulty;
    }

    public String findBlockHash(Block block) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        int nonce = 0;
        String hash;
        do {
            nonce++;
            hash = SHA256Encrypt.encrypt(block.getPreviousBlockHash() + block.getTimeStamp() + nonce + block.getTransactionList().toString());
        } while (!hash.substring(0, difficulty).equals(target));
        LogUtil.info("PoW挖矿完成，Nonce值：" + nonce + "，哈希：" + hash);
        return hash;
    }

    public boolean validateBlock(Block block) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        return block.getBlockHash().substring(0, difficulty).equals(target);
    }
}
