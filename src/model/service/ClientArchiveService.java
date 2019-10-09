package model.service;

import java.util.List;

import bean.ClientArchive;
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

	/**
	 * 查询来访者正在预约的申请
	 * @param clientId
	 * @return
	 */
	public List<ClientArchive> onSubList(Integer clientId) {
		return clientArchiveDao.listClientArchive(clientId,0,1);
	}

	
	/**
	 * 查询来访者已经完成的预约
	 * @param clientId
	 * @return
	 */
	public List<ClientArchive> clientConsult(Integer clientId) {
		return clientArchiveDao.listClientArchive(clientId,2,3);
	}
}
