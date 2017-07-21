package org.jumutang.project.weixinMng.gameOne.model;



import java.io.Serializable;

import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;

/*
 * 游戏1的Bean
 */

public class GameOneMode implements Serializable {

	private static final long serialVersionUID = -5065336974131274470L;
	

	private User_gameMngMode USER_GAMEMNGMODE;
	
	// 今天游戏购买次数
	private int TODAY_GAME_BUY;
	
	// 今天新手游戏购买次数
	private int TODAY_GAME_BUY4;

	// 今天老手游戏购买次数
	private int TODAY_GAME_BUY5;
	
	// 加油机购买次数
	private int OIL_MACHINE_BUY;
	
	// 车位购买次数
	private int PARK_BUY;
	
	// 新手已阅读
	private int GAME2KNOWFLAG;
	
	// 老手已阅读
	private int GAME1KNOWFLAG;
	
	// 新手模式游戏次数信息
	private T1UserGametypeMode  USERGAMETYPEMODE4;
	
	// 老手模式次数信息
	private T1UserGametypeMode  USERGAMETYPEMODE5;
	
	
	public int getTODAY_GAME_BUY4() {
		return TODAY_GAME_BUY4;
	}

	public void setTODAY_GAME_BUY4(int tODAY_GAME_BUY4) {
		TODAY_GAME_BUY4 = tODAY_GAME_BUY4;
	}

	public int getTODAY_GAME_BUY5() {
		return TODAY_GAME_BUY5;
	}

	public void setTODAY_GAME_BUY5(int tODAY_GAME_BUY5) {
		TODAY_GAME_BUY5 = tODAY_GAME_BUY5;
	}
	
	public T1UserGametypeMode getUSERGAMETYPEMODE4() {
		return USERGAMETYPEMODE4;
	}

	public void setUSERGAMETYPEMODE4(T1UserGametypeMode uSERGAMETYPEMODE4) {
		USERGAMETYPEMODE4 = uSERGAMETYPEMODE4;
	}

	public T1UserGametypeMode getUSERGAMETYPEMODE5() {
		return USERGAMETYPEMODE5;
	}

	public void setUSERGAMETYPEMODE5(T1UserGametypeMode uSERGAMETYPEMODE5) {
		USERGAMETYPEMODE5 = uSERGAMETYPEMODE5;
	}

	public int getGAME2KNOWFLAG() {
		return GAME2KNOWFLAG;
	}

	public void setGAME2KNOWFLAG(int gAME2KNOWFLAG) {
		GAME2KNOWFLAG = gAME2KNOWFLAG;
	}

	public int getGAME1KNOWFLAG() {
		return GAME1KNOWFLAG;
	}

	public void setGAME1KNOWFLAG(int gAME1KNOWFLAG) {
		GAME1KNOWFLAG = gAME1KNOWFLAG;
	}

	public User_gameMngMode getUSER_GAMEMNGMODE() {
		return USER_GAMEMNGMODE;
	}

	public void setUSER_GAMEMNGMODE(User_gameMngMode uSER_GAMEMNGMODE) {
		USER_GAMEMNGMODE = uSER_GAMEMNGMODE;
	}

/*	public int getTODAY_GAME_BUY() {
		return TODAY_GAME_BUY;
	}

	public void setTODAY_GAME_BUY(int tODAY_GAME_BUY) {
		TODAY_GAME_BUY = tODAY_GAME_BUY;
	}*/

	public int getOIL_MACHINE_BUY() {
		return OIL_MACHINE_BUY;
	}

	public void setOIL_MACHINE_BUY(int oIL_MACHINE_BUY) {
		OIL_MACHINE_BUY = oIL_MACHINE_BUY;
	}

	public int getPARK_BUY() {
		return PARK_BUY;
	}

	public void setPARK_BUY(int pARK_BUY) {
		PARK_BUY = pARK_BUY;
	}
	
}
