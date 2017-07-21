package org.jumutang.project.weixinMng.gameOne.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jumutang.project.weixinMng.gameOne.dao.IGameOneDao;
import org.jumutang.project.weixinMng.mallMng.model.User_gameMngMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

@Repository
public class GameOneDaoImpl implements IGameOneDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void updateUserGameInfo_Times(final User_gameMngMode user_gameMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_game ");
		sqlBuffer.append(" SET DAY_TIMES=? ");
//		sqlBuffer.append(" , GAME_USED_TIMES=? ");
		sqlBuffer.append(" , GAME_ALL_TIMES=? ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, user_gameMngMode.getDAY_TIMES());
//				ps.setString(i++, user_gameMngMode.getGAME_USED_TIMES());
				ps.setString(i++, user_gameMngMode.getGAME_ALL_TIMES());
				ps.setString(i++, user_gameMngMode.getID());
				
				return ps;
			}
		});

	}

	@Override
	public void update_GameOneUsedTimes(final User_gameMngMode user_gameMngMode) {
		// TODO Auto-generated method stub
		final StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" UPDATE t1_user_game ");
		sqlBuffer.append(" SET DAY_TIMES=? ");
		sqlBuffer.append(" , GAME_USED_TIMES=? ");
		sqlBuffer.append(" WHERE ID = ?");

		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(java.sql.Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sqlBuffer.toString());
				int i = 1;
				ps.setString(i++, user_gameMngMode.getDAY_TIMES());
				ps.setString(i++, user_gameMngMode.getGAME_USED_TIMES());
				ps.setString(i++, user_gameMngMode.getID());
				
				return ps;
			}
		});

	}

	

}
