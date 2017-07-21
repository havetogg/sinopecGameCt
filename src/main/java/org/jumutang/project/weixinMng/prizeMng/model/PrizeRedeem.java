package org.jumutang.project.weixinMng.prizeMng.model;

/**
 * @Auther: Tinny.liang
 * @Description: 中奖商品兑换
 * @Date: Create in 16:28 2017/7/11
 * @Modified By:
 */
public class PrizeRedeem {

    private String id;

    private String userId;

    private String prizeId;

    private String RedeemCode;

    private String winningTime;

    private String endTime;

    private String receiveTime;

    private String status;

    private String redpkgId;

    private String oilOpenId;

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

    public String getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    public String getRedeemCode() {
        return RedeemCode;
    }

    public void setRedeemCode(String redeemCode) {
        RedeemCode = redeemCode;
    }

    public String getWinningTime() {
        return winningTime;
    }

    public void setWinningTime(String winningTime) {
        this.winningTime = winningTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRedpkgId() {
        return redpkgId;
    }

    public void setRedpkgId(String redpkgId) {
        this.redpkgId = redpkgId;
    }

    public String getOilOpenId() {
        return oilOpenId;
    }

    public void setOilOpenId(String oilOpenId) {
        this.oilOpenId = oilOpenId;
    }
}
