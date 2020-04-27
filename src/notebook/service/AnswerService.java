package notebook.service;

import java.sql.SQLException;

import notebook.dao.BoardAnswerDao;
import notebook.dao.BoardAnswerDaoImpl;
import notebook.domain.BoardAnswer;
import notebook.exception.CannotModifyException;
import notebook.exception.NotFoundException;

public class AnswerService {
	private static BoardAnswerDao ans = new BoardAnswerDaoImpl();
	
	public static void delete(int ansNo, int qnaNo) throws SQLException, NotFoundException, CannotModifyException{
		int result = ans.delete(ansNo, qnaNo);
		if(result == 0 ) {
			throw new SQLException("�������� �ʾҽ��ϴ�.");
		}
	}
	
	/**
	 * �亯���_�亯 ���� 1�� ����(0:�̴亯, 1:�亯�Ϸ�)
	 * @param answer
	 * @throws SQLException
	 * @throws CannotModifyException
	 */
	public static void insert(BoardAnswer answer) throws SQLException, CannotModifyException{
		int result = ans.insert(answer);
		if(result == 0) {
			throw new SQLException("��ϵ��� �ʾҽ��ϴ�.");
		}
	}
	
	/**
	 * �亯 ����
	 * @param answer
	 * @throws SQLException
	 * @throws CannotModifyException
	 */
	public static void update(BoardAnswer answer) throws SQLException, CannotModifyException{
		int result = ans.update(answer);
		if(result == 0) {
			throw new CannotModifyException("�������� �ʾҽ��ϴ�.");
		}
	}
}
