package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.PurchaseService;

/**
 * ��ۻ��� ����(������)
 * @author ���ȣ
 *
 */
public class PurDeliveryStateController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		String deliveryState = request.getParameter("deliveryState");
		if(orderNo == null || orderNo.equals("") || deliveryState == null || deliveryState.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		PurchaseService.deliveryState(Integer.parseInt(orderNo), Integer.parseInt(deliveryState));
		ModelAndView mv = new ModelAndView(false, "������ ������");
		return mv;
	}

}
