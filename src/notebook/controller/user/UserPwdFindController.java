package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Users;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ��й�ȣ ã��
 * @author ���ȣ
 *
 */
public class UserPwdFindController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String phone = request.getParameter("phone");
		if(userId == null || userId.equals("") || phone == null || phone.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		Users users = UserService.selectByIdPhone(userId, phone);
		String pwd = users.getPwd();
		request.setAttribute("pwd", pwd);
		request.setAttribute("userId", userId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("�α�������");
		return mv;
	}

}
