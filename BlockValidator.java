public class BlockValidator {
    public static boolean validateBlock(Block block, Block previousBlock, int difficulty) {
        if (!validateBlockHash(block)) return false;
        if (!validatePreviousHash(block, previousBlock)) return false;
        if (!validateDifficulty(block, difficulty)) return false;
        if (!validateTransactions(block)) return false;
        return true;
    }

    private static boolean validateBlockHash(Block block) {
        return block.getBlockHash().equals(block.calculateBlockHash());
    }

    private static boolean validatePreviousHash(Block block, Block previousBlock) {
        return block.getPreviousBlockHash().equals(previousBlock.getBlockHash());
    }

    private static boolean validateDifficulty(Block block, int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        return block.getBlockHash().substring(0, difficulty).equals(target);
    }

    private static boolean validateTransactions(Block block) {
        for (Transaction tx : block.getTransactionList()) {
            if (!tx.verifyTransaction()) return false;
        }
        return true;
    }
}
