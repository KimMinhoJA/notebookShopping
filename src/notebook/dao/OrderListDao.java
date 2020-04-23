package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.OrderList;

public interface OrderListDao {
	/**
	 * ��ǰ�� ���ų��� ����
	 * @param serialNum
	 * @return
	 */
	public List<OrderList> selectBySerialNum(String serialNum) throws SQLException;
	
	/**
	 * ��ü ���ų��� ����
	 * @return
	 */
	public List<OrderList> selectAll() throws SQLException;
	
	/**
	 * ���ų��� ����(�κ�ȯ��)
	 * @param orderNo
	 * @param serialNum
	 * @return
	 */
	public int delete(int orderNo, String serialNum) throws SQLException;
	
	/**
	 * ���Ž�
	 * @param list
	 * @return
	 */
	public int insert(OrderList list) throws SQLException;
}
