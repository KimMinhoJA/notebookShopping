package notebook.controller.ans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardAnswer;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.AnswerService;

public class AnsInsertController implements Controller {
	
	/**
	 * �亯 �ޱ�_qnaNo, content _ �亯���� 1�� ����.
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qnaNo = request.getParameter("qnaNo");
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		
		if(qnaNo == null || qnaNo.equals("") || content == null || content.equals("") || userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}

		if(userId.equals("admin")) {
			throw new NotFoundException("�����ڸ� �����մϴ�.");
		}
		BoardAnswer board = new BoardAnswer();
		board.setQnaNo(Integer.parseInt(qnaNo));
		board.setContent(content);
		AnswerService.insert(board);
		
		ModelAndView mv = new ModelAndView();
		mv.setRedirect(true);
		//qnaNo���� �Խù� �󼼺���� �̵�
		mv.setViewName("");
		return mv;
	}

}