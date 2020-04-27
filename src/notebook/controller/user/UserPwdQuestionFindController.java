package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Users;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ����ȣ�� ���̵�� ���� ��������
 * @author ���ȣ
 *
 */
public class UserPwdQuestionFindController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String userId = request.getParameter("userId");
		String phone = request.getParameter("phone");
		if(userId == null || userId.equals("") || phone == null || phone.equals("") || name ==null ||name.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		Users user = UserService.selectByIdPhoneName(userId, phone, name);
		String question = user.getQuestion().getQuestion();
		request.setAttribute("question", question);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("�������� â����");
		return mv;
	}

}
