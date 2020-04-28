package notebook.controller.review;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
//github.com/KimMinhoJA/notebookShopping
import notebook.service.ReviewService;

public class ReviewDeleteController implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String)request.getSession().getAttribute("id");
		String reviewNo = request.getParameter("reviewNo");
		String imgName = request.getParameter("imgName");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 2);
		request.setAttribute("jsonObj", jsonObj);
		
		if(userId == null || userId.equals("") || reviewNo == null | reviewNo.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		ReviewService.delete(Integer.parseInt(reviewNo), userId);
		
		if(imgName != null && !imgName.equals("")) {
			String path = request.getServletContext().getRealPath("/save");
			File file = new File(path + "/" + imgName);
			file.delete();
		}
		
		jsonObj.replace("status", 1);
		ModelAndView mv = new ModelAndView(true, "�ı�������");
		
		return mv;
	}
}