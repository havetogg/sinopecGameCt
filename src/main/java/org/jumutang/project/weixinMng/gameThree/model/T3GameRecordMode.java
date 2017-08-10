package org.jumutang.project.weixinMng.gameThree.model;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 11:28 2017/7/25
 * @Modified By:
 */
public class T3GameRecordMode {

    private String id;

    private String userId;

    private String gameTime;
    // 10 初级 30 中级 50 高级 1钻石
    private String gameType;

    private String refId;

    private String prizeType;

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

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }
}
