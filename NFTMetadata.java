public class NFTMetadata {
    private String tokenName;
    private String metadataUrl;
    private String owner;
    private long createTime;

    public NFTMetadata(String tokenName, String metadataUrl, String owner) {
        this.tokenName = tokenName;
        this.metadataUrl = metadataUrl;
        this.owner = owner;
        this.createTime = System.currentTimeMillis();
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTokenName() { return tokenName; }
    public String getMetadataUrl() { return metadataUrl; }
    public String getOwner() { return owner; }
    public long getCreateTime() { return createTime; }

    @Override
    public String toString() {
        return "NFT名称：" + tokenName + "，拥有者：" + owner + "，元数据地址：" + metadataUrl;
    }
}
