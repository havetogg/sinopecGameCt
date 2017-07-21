package org.jumutang.project.weixinMng.gameTwo.model;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 15:03 2017/7/12
 * @Modified By:
 */
public class GameTwoMode {

    // 今天剩余游戏次数
    private String todayRemainTimes;

    //当前能量数
    private String energyNum;

    //老司机
    private String know;

    public String getTodayRemainTimes() {
        return todayRemainTimes;
    }

    public void setTodayRemainTimes(String todayRemainTimes) {
        this.todayRemainTimes = todayRemainTimes;
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
}
