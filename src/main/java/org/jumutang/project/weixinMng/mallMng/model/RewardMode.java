package org.jumutang.project.weixinMng.mallMng.model;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 16:44 2017/7/27
 * @Modified By:
 */
public class RewardMode {

    private String id;

    private String userId;

    //0 首充10元奖励 1 充值奖励 2 油滴奖励
    private String rewardType;

    private String rewardName;

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

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
