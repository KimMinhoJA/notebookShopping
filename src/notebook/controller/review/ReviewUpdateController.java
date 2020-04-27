package notebook.controller.review;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardReview;
import notebook.exception.CannotModifyException;
import notebook.exception.NotEnoughParameterException;
import notebook.service.ReviewService;

public class ReviewUpdateController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		String content = request.getParameter("content");
		String useId = request.getParameter("userId");
		String reviewNo = request.getParameter("reviewNo");
		String userId = request.getParameter("userId");
		String imgName = request.getParameter("imgName");
		String serialNum = request.getParameter("serialNum");
		String grade = request.getParameter("grade");
		
		if(userId == null || userId.equals("") || serialNum == null || serialNum.equals("")
				|| grade==null || grade.equals("") ) {
			
			
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		
		try {
			boolean bFind = false;
			
			List<BoardReview> ls = ReviewService.searchByUserId(userId);
			for(BoardReview br : ls) {
				if(br.getReviewNo() == Integer.parseInt(reviewNo)) {
					bFind = true;
					BoardReview review = new BoardReview(Integer.parseInt(reviewNo), userId, imgName, content, serialNum, Integer.parseInt(grade) );
					ReviewService.update(review);
					 mv = new ModelAndView(true, "reviewSelectBySerialNum page");
					break;
				}
				
				if(!bFind) {
					throw new CannotModifyException("�����Ϸ��� ����� �ٸ� �ۼ����� ���� �Դϴ�.");
				}
				
			}
			
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			String url = "errorView/error.jsp";
		}
		
		
		return mv;
		
	
	}

	
}
