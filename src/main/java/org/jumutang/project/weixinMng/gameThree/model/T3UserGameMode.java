package org.jumutang.project.weixinMng.gameThree.model;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 11:32 2017/7/25
 * @Modified By:
 */
public class T3UserGameMode {
    private String id;

    private String userId;

    private String gameAllTimes;

    private String lastGameTime;

    private String energyNum;

    private String know;

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

    public String getGameAllTimes() {
        return gameAllTimes;
    }

    public void setGameAllTimes(String gameAllTimes) {
        this.gameAllTimes = gameAllTimes;
    }

    public String getLastGameTime() {
        return lastGameTime;
    }

    public void setLastGameTime(String lastGameTime) {
        this.lastGameTime = lastGameTime;
    }

    public String getEnergyNum() {
        return energyNum;
    }

    public void setEnergyNum(String energyNum) {
        this.energyNum = energyNum;
    }

    public String getKnow() {
        return know;
    }

    public void setKnow(String know) {
        this.know = know;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
