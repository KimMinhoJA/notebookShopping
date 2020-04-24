package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.Users;

public interface UserDao {
	/**
	 * �α���, ������������
	 * @param userId
	 * @return
	 */
	public Users selectById(String userId) throws SQLException;
	
	/**
	 * ��üȸ�� �˻�(������)
	 * @return
	 */
	public List<Users> selectAll() throws SQLException;
	
	/**
	 * ���� ��������(����� ���� ��й�ȣ, �ּ�, ����ó, ȸ��Ż��), ������ ����(��Ȱ��ȭ)
	 * @param user
	 * @return
	 */
	public int updateStatus(Users user) throws SQLException;
	
	/**
	 * ȸ������
	 * @param user
	 * @return
	 */
	public int insert(Users user) throws SQLException;
	
	
	/**
	 * ȸ��Ż��, ������ ��� (��Ȱ��ȭ)
	 * @param userId
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public int withdrawMember(String userId, String password)throws SQLException;
}
