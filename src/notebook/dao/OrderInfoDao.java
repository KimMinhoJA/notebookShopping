package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.OrderInfo;

public interface OrderInfoDao {
	/**
	 * ��ȣ�� ���ų��� �˻�(���� �󼼺���)
	 * @param orderNo
	 * @return
	 */
	public OrderInfo selectByNo(int orderNo) throws SQLException;
	
	/**
	 * ������ ���ų��� ����(���ų�����)
	 * @param userId
	 * @return
	 */
	public List<OrderInfo> selectById(String userId) throws SQLException;
	
	/**
	 * ��ü ���ų���(������)
	 * @return
	 */
	public List<OrderInfo> selectAll() throws SQLException;
	
	/**
	 * ��ۻ���, ȯ�ҽ�
	 * @param info
	 * @return
	 */
	public int update(OrderInfo info) throws SQLException;
	
	/**
	 * ��üȯ��
	 * @param orderNo
	 * @return
	 */
	public int delete(int orderNo) throws SQLException;
	
	/**
	 * ���Ž�
	 * @param info
	 * @return
	 */
	public int insert(OrderInfo info) throws SQLException;
}
