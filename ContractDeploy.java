public class ContractDeploy {
    private SmartContractEngine engine;
    private BlockChainLedger blockChain;

    public ContractDeploy(SmartContractEngine engine, BlockChainLedger blockChain) {
        this.engine = engine;
        this.blockChain = blockChain;
    }

    public String deployNFTContract(String creator) {
        NFTContract nftContract = new NFTContract(creator);
        engine.deployContract(nftContract);
        blockChain.addTransaction(new Transaction(null, null, 0));
        blockChain.minePendingTransactions();
        return nftContract.getContractAddress();
    }

    public String deployTraceContract(String creator) {
        SupplyChainTrace traceContract = new SupplyChainTrace(creator);
        engine.deployContract(traceContract);
        blockChain.addTransaction(new Transaction(null, null, 0));
        blockChain.minePendingTransactions();
        return traceContract.getContractAddress();
    }

    public String deployCertContract(String creator) {
        DataCertification certContract = new DataCertification(creator);
        engine.deployContract(certContract);
        blockChain.addTransaction(new Transaction(null, null, 0));
        blockChain.minePendingTransactions();
        return certContract.getContractAddress();
    }
}
