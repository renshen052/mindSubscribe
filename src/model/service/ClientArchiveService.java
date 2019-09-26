package model.service;

import model.dao.ClientArchiveDao;

public class ClientArchiveService {

	ClientArchiveDao clientArchiveDao = new ClientArchiveDao();

	/**
	 * 查询共有多少条咨询记录
	 * @return
	 */
	public int getClientArchiveNum() {
		return clientArchiveDao.getClientArchiveNum();
	}
}
