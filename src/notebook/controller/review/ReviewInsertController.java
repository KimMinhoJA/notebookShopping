 package notebook.controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardReview;
import notebook.exception.NotEnoughParameterException;
import notebook.service.ReviewService;

public class ReviewInsertController implements Controller {
	
	
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String saveDir = request.getServletContext().getRealPath("/save");
		int maxSize = 1024*1024*100;
		String encoding = "UTF-8";
		
		MultipartRequest m = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		
		
		String userId = m.getParameter("userId");
		String content = m.getParameter("content");
		String serialNum = m.getParameter("serialNum");
		String grade = m.getParameter("grade");
		
		if(userId == null || userId.equals("") || serialNum == null || serialNum.equals("")
				|| grade==null || grade.equals("") ) {
			
			
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}

		BoardReview review = new BoardReview(userId, null, content, serialNum, Integer.parseInt(grade) );
		
		if(m.getFile("imgName") != null) {
			String imgName = m.getFilesystemName("imgName");
			review.setImgName(imgName);
		}
		
		ReviewService.insert(review);
		ModelAndView mv = new ModelAndView(false, "reviewSelectBySerialNum page");
		return mv;
	}
}
		
	


