package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Users;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ���� �ڵ������� ���̵� ã�� -> ���� ��������
 * @author ���ȣ
 *
 */
public class UserIdQuestionFindController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		if(phone == null||phone.equals("") || name == null || name.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		Users user = UserService.selectByPhoneName(phone, name);
		String question = user.getQuestion().getQuestion();
		//�ڵ��� ��ȣ�� �亯���� ã�ƿ���
		request.setAttribute("question", question);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("���� �亯 â����");
		return mv;
	}
}
