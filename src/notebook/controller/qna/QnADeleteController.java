package notebook.controller.qna;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.QnAService;;

public class QnADeleteController implements Controller {

	/**
	 * �Խù� ����.
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qnaNo = request.getParameter("qnaNo");
		String userId = request.getParameter("userId");
		if(qnaNo == null || qnaNo.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		QnAService.delete(Integer.parseInt(qnaNo), userId);
		ModelAndView mv = new ModelAndView();
		mv.setRedirect(true);//true �̸� Redirect�� ����.
		mv.setViewName("qna�Խ���?");//�̵��� url
		return mv;
	}

}

