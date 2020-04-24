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
	 * ��ۻ��� ����
	 * @param info
	 * @return
	 */
	public int updateDelivery(int orderNo, int deliveryState) throws SQLException;
	
	/**
	 * ȯ�� ��û
	 * @param orderNo
	 * @param request  true�� ȯ�ҿ�û, false�� ȯ�����
	 * @return
	 * @throws SQLException
	 */
	public int updateRefundRequest(int orderNo, boolean request) throws SQLException;
	
	/**
	 * ����� ����
	 * @param orderNo
	 * @param addrDelivery
	 * @return
	 * @throws SQLException
	 */
	public int updateAddr(int orderNo, String addrDelivery) throws SQLException;
	
	/**
	 * ȯ��(������)
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
