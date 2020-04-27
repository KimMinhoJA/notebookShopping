package notebook.controller.cart;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.CartService;

/**
 * ��ٱ��� ����
 * @author kosta
 *
 */
public class CartEmptyController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		if(userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		CartService.cartEmpty(userId);
		ModelAndView mv = new ModelAndView(true, "��ٱ��� ������");
		return mv;
	}

}
