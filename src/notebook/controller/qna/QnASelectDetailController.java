package notebook.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardQnA;
import notebook.exception.NotFoundException;
import notebook.service.QnAService;

public class QnASelectDetailController implements Controller {

	/**
	 * qna �ϳ� �󼼺���_��ȸ�� ����
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qnaNo = request.getParameter("qnaNo");
		if(qnaNo == null || qnaNo.equals("")) {
			throw new NotFoundException("�ش� �Խù��� ������ �������� �ʽ��ϴ�.");
		}
		String flag = request.getParameter("flag");
		boolean state = flag == null ? false : true;//true �̸� null �̱� ������ ��ȸ�� ���� x
		
		BoardQnA board = QnAService.selectByNo(Integer.parseInt(qnaNo), state);
		
		request.setAttribute("board", board);
		ModelAndView mv = new ModelAndView(false, "�Խù� �󼼺���");//�Խù� �󼼺��� ��
		return mv;
	}

}

