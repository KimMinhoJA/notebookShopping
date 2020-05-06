package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Users;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.UserService;

/**
 * ȸ�� �α���
 * @author kosta
 *
 */
public class UserLoginController implements Controller {
	
	/**
	 * �α���
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���̵�� ��й�ȣ�� �޾ƿ´�.
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		
		if(userId == null || userId.equals("") || pwd == null || pwd.equals("")) {
			throw new NotEnoughParameterException("�Է°��� �����մϴ�.");
		}
		
		Users user = UserService.selectById(userId);
		if(user.getPwd().equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("id", userId);//���ǿ� ����
		}else {
			throw new NotFoundException("ȸ�������� ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���.");
		}
		ModelAndView mv = new ModelAndView(true, "note");
		
		if(userId.equals("admin")) {
			mv.setViewName("managerIndex.jsp");
		}
		
		return mv;
	}

}
