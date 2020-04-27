package notebook.controller.cart;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Product;
import notebook.exception.EmptyStockException;
import notebook.service.CartService;

public class CartEmptyController implements Controller {

	//��ٱ��� ���� ���

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = request.getParameter("userId");
		String quantity = request.getParameter("quantity");
		String serialNum = request.getParameter("serialNum");
		
		if(quantity== null) {
			throw new EmptyStockException("��ٱ��Ͽ� ��ǰ�� ���� ��� �� �����ϴ�.");
		}
		
		CartService.cartEmpty(userId);
		ModelAndView mv = new ModelAndView(true, "cart");
		return mv;
	}

}
