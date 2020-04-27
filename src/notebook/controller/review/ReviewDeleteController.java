package notebook.controller.review;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.ReviewService;

public class ReviewDeleteController implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String reviewNo = request.getParameter("reviewNo");
		String imgName = request.getParameter("imgName");
		if(userId == null || userId.equals("") || reviewNo == null | reviewNo.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		ReviewService.delete(Integer.parseInt(reviewNo), userId);
		
		if(imgName != null && !imgName.equals("")) {
			String path = request.getServletContext().getRealPath("/save");
			File file = new File(path + "/" + imgName);
			file.delete();
		}
		
		ModelAndView mv = new ModelAndView(true, "�ı�������");
		
		return mv;
	}
}