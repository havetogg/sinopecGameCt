package org.jumutang.project.weixinMng.prizeMng.model;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:01 2017/7/20
 * @Modified By:
 */
public class PrizePool {

    private String id;

    private String userId;

    private String prizePool;

    private String usedPool;

    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(String prizePool) {
        this.prizePool = prizePool;
    }

    public String getUsedPool() {
        return usedPool;
    }

    public void setUsedPool(String usedPool) {
        this.usedPool = usedPool;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
