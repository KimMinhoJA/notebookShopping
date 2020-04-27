package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.BoardReview;
import notebook.exception.CannotModifyException;
import notebook.exception.NotFoundException;

public interface BoardReviewDao {
	/**
	 * ��ǰ�� �ı� ����
	 * @param serialNum
	 * @return
	 */
	public List<BoardReview> selectBySerialNum(String serialNum) throws SQLException;
	
	/**
	 * �ı� ����
	 * @param review
	 * @return
	 */
	public int update(BoardReview review) throws SQLException;
	
	/**
	 * �ı� ����
	 * @param reviewNo
	 * @return
	 */
	public int delete(int reviewNo) throws SQLException, NotFoundException, CannotModifyException;
	
	/**
	 * �ı� ���
	 * @param review
	 * @return
	 */
	public int insert(BoardReview review) throws SQLException, NotFoundException, CannotModifyException;
}
