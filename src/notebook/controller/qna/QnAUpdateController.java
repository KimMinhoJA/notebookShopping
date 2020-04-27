
package notebook.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardQnA;
import notebook.exception.NotEnoughParameterException;
import notebook.service.QnAService;

public class QnAUpdateController implements Controller {

	/**
	 * 게시물 수정하기 _ 제목 내용 비밀번호 수정
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//subject, content, password
				String subject = request.getParameter("subject");
				String content = request.getParameter("content");
				String password = request.getParameter("password");
				String qnaNo = request.getParameter("qnaNo");
				if(subject == null || subject.equals("") || content == null || content.equals("")||
					password == null || password.equals("")|| qnaNo == null || qnaNo.equals("")) {
					throw new NotEnoughParameterException("입력값이 충분하지 않습니다.");
				}
				BoardQnA board = new BoardQnA();
				board.setSubject(subject);
				board.setContent(content);
				board.setPassword(password);
				board.setQnaNo(Integer.parseInt(qnaNo));
				
				QnAService.update(board);//업데이트
				ModelAndView mv = new ModelAndView();
				//게시물 상세로
				mv.setViewName("게시물로 가자");
				return mv;
	}

}

