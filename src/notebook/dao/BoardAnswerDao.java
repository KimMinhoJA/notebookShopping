package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.BoardAnswer;

public interface BoardAnswerDao {
	/**
	 * qna�Խ��� �ϳ� ������ �� �亯 ��������
	 * @param qnaNo
	 * @return
	 * @throws SQLException 
	 */
	public List<BoardAnswer> selectByQnaNo(int qnaNo) throws SQLException;
	
	/**
	 * �亯 ����
	 * @param answer
	 * @return
	 */
	public int update(BoardAnswer answer) throws SQLException;
	
	/**
	 * �亯 ����
	 * @param ansNo
	 * @return
	 */
	public int delete(int ansNo) throws SQLException;
	
	/**
	 * �亯 ���
	 * @param answer
	 * @return
	 */
	public int insert(BoardAnswer answer) throws SQLException;
}
