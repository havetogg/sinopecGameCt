package org.jumutang.project.weixinMng.prizeMng.model;

/**
 * @Auther: Tinny.liang
 * @Description: 得到钻石记录类
 * @Date: Create in 16:00 2017/7/11
 * @Modified By:
 */
public class DiamondRecord {

    private String id;

    private String gameId;

    private String userId;
    //钻石数
    private String diamonds;

    private String createTime;

    private String status;

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

    public String getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(String diamonds) {
        this.diamonds = diamonds;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
