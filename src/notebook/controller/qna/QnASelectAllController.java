package notebook.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardQnA;
import notebook.service.QnAService;


public class QnASelectAllController implements Controller {
	//qna�Խ��� �� �� / ������ / ���Խ��� ���� �����ֱ�

	/**
	 * ��� �Խ��� ���� �����ֱ� 
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<BoardQnA> list = QnAService.selectAll();
			
			request.setAttribute("list", list);
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("???");
			return mv;
		
	}
	
	

}
