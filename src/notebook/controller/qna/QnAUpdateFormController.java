package notebook.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardQnA;
import notebook.exception.NotEnoughParameterException;
import notebook.service.BoardQnAService;

public class QnAUpdateFormController implements Controller {
	
	/**
	 * �����ϱ� ��ư ������ ������ �̵�
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qnaNo = request.getParameter("qnaNo");
		if(qnaNo == null || qnaNo.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		boolean flag = false;
		
		BoardQnA list = BoardQnAService.selectByNo(Integer.parseInt(qnaNo), flag);
		request.setAttribute("list", list);
		ModelAndView mv = new ModelAndView(false, "");//�Խù� �����ϱ�.jsp
		return mv;
		
	}

}
