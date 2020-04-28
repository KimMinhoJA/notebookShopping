package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.PurchaseService;

/**
 * 환불기능
 * @author 김민호
 *
 */
public class PurRefundController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		String userId = request.getParameter("userId");
		
		if(orderNo == null || orderNo.equals("") || userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("입력값이 충분하지 않습니다.");
		}
		if("admin".equals(userId)) {
			throw new NotFoundException("관리자만 가능합니다.");
		}
		
		PurchaseService.refundOrder(Integer.parseInt(orderNo));
		ModelAndView mv = new ModelAndView(false, "마이페이지");
		return mv;
	}

}
