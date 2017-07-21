package org.jumutang.project.weixinMng.gameTwo.model;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:37 2017/7/12
 * @Modified By:
 */
public class T2GameRecordMode {

    //id userid type

    private String id;

    private String userId;

    private String refId;

    private String gameTime;

    private String type;

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

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
