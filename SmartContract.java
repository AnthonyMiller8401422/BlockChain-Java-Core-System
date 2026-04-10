public abstract class SmartContract {
    protected String contractAddress;
    protected String creatorAddress;
    protected long deployTime;
    protected boolean isActive;

    public SmartContract(String creatorAddress) {
        this.creatorAddress = creatorAddress;
        this.deployTime = System.currentTimeMillis();
        this.contractAddress = generateContractAddress();
        this.isActive = true;
    }

    private String generateContractAddress() {
        String input = creatorAddress + deployTime + Math.random();
        return SHA256Encrypt.encrypt(input).substring(0, 40);
    }

    public abstract void execute();
    public abstract void destroy();
    public abstract String getContractInfo();

    public String getContractAddress() { return contractAddress; }
    public boolean isActive() { return isActive; }
}
