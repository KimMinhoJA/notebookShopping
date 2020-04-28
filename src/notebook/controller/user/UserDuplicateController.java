package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.DuplicateException;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ���̵� �ߺ� üũ(ajax���)
 * @author ���ȣ
 *
 */
public class UserDuplicateController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 2);
		request.setAttribute("jsonObj", jsonObj);
		
		if(userId == null || userId.equals("")) {
			request.setAttribute("jsonObj", jsonObj);
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		try {
			if(UserService.selectById(userId) != null) {
				throw new DuplicateException("���̵� �ߺ��Դϴ�.");
			}
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		jsonObj.replace("status", 1);
		return null;
	}

}
