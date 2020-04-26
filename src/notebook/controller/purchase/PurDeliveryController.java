package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.PurchaseService;

/**
 * ��ǰ ������� ����
 * @author ���ȣ
 *
 */
public class PurDeliveryController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		String addrDelivery = request.getParameter("deliveryAddr");
		
		if(orderNo == null || orderNo.equals("") || addrDelivery == null || addrDelivery.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		PurchaseService.updateAddr(Integer.parseInt(orderNo), addrDelivery);
		ModelAndView mv = new ModelAndView(false, "������������");
		return mv;
	}

}
