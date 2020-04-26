package notebook.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Product;
import notebook.exception.NotEnoughParameterException;
import notebook.service.ProductService;

/**
 * ȸ��, �𵨸����� ��ǰ�� �˻��ϱ�
 * @author ���ȣ
 *
 */
public class ProSearchController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String target = request.getParameter("target");
		String search = request.getParameter("search");
		
		if(target == null || target.equals("") || search == null || search.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		List<Product> list = ProductService.searchProduct(target, search);
		
		request.setAttribute("list", list);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("��ǰ �˻� ����");
		return mv;
	}

}
