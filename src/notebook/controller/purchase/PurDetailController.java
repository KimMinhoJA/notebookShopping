package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.OrderInfo;
import notebook.exception.NotEnoughParameterException;
import notebook.service.PurchaseService;

/**
 * ���ų��� �󼼺���
 * @author ���ȣ
 *
 */
public class PurDetailController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		if(orderNo == null ||orderNo.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		OrderInfo info = PurchaseService.selectByNo(Integer.parseInt(orderNo));
		request.setAttribute("info", info);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("���ų��� �󼼺���");
		return mv;
	}

}
