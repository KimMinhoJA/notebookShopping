package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.Product;

public interface ProductDao {
	/**
	 * ��ǰ �󼼺���
	 * @param serialNum
	 * @return
	 */
	public Product selectByNum(String serialNum) throws SQLException;

	/**
	 * �������� �˻�
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<Product> selectByPriceRange(int minPrice, int maxPrice) throws SQLException;
	
	/**
	 * �Ż�ǰ 3�� �˻�
	 * @return
	 */
	public List<Product> selectNewProducts() throws SQLException;
	
	/**
	 * ���������� 3�� �˻�
	 * @return
	 */
	public List<Product> selectBestProducts() throws SQLException;
	
	/**
	 * ������ ��ǰ 3�� �˻�
	 * @return
	 */
	public List<Product> selectRendomProducts() throws SQLException;
	
	/**
	 * ��ü��ǰ �����ؼ� �˻� (target�� NULL�̸� ��ȣ�� �ʱⰪ)
	 * @param target
	 * @return
	 */
	public List<Product> selectSortProduct(String target) throws SQLException;
	
	/**
	 * ��ǰ ���� ����, ���Ž� ��� ����, ȯ�ҽ� ��� ����
	 * @param product
	 * @return
	 */
	public int update(Product product) throws SQLException;
	
	/**
	 * ��ǰ �߰�
	 * @param product
	 * @return
	 */
	public int insert(Product product) throws SQLException;
}
