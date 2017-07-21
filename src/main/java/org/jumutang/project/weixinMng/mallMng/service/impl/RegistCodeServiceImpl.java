package org.jumutang.project.weixinMng.mallMng.service.impl;

import java.util.HashMap;

import org.jumutang.project.weixinMng.mallMng.dao.IRegistCodeDao;
import org.jumutang.project.weixinMng.mallMng.model.RegistCodeMode;
import org.jumutang.project.weixinMng.mallMng.service.IRegistCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistCodeServiceImpl implements IRegistCodeService {

	@Autowired
	private IRegistCodeDao registCodeDao;

	@Override
	@Transactional
	public int insertRegistCodeInfoVolume(HashMap<String, String> amap) {
		return registCodeDao.insertRegistCodeInfoVolume(amap);
	}

	@Override
	public RegistCodeMode queryRegistCodeInfo(HashMap<String, String> amap) {
		return registCodeDao.queryRegistCodeInfo(amap);
	}

}
