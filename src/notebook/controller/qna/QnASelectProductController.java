package notebook.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.dao.BoardReviewDao;
import notebook.dao.BoardReviewDaoImpl;
import notebook.domain.BoardQnA;
import notebook.domain.BoardReview;
import notebook.exception.NotFoundException;
import notebook.service.QnAService;

public class QnASelectProductController implements Controller {
	
	/**
	 * ��ǰ�󼼺��⿡�� qna�Խ��� ������ ��
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String serialNum = request.getParameter("serialNum");
		
		if(serialNum == null || serialNum.equals("")) {
			throw new NotFoundException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		List<BoardQnA> list = QnAService.selectBySerialNum(serialNum);
		request.setAttribute("list", list);
		//��ǰ�� �ı� �Խ��Ǻ���
		BoardReviewDao review = new BoardReviewDaoImpl();
		List<BoardReview> reviewlist = review.selectBySerialNum(serialNum);
		request.setAttribute("reviewlist", reviewlist);
		//�ı� �Խù� ��������
		//review.insert(review);
		
		ModelAndView mv = new ModelAndView(false, "");
		return mv;
	}

}
