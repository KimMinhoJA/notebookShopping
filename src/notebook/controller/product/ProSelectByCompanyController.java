package notebook.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Product;
import notebook.exception.NotEnoughParameterException;
import notebook.service.ProductService;

public class ProSelectByCompanyController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String company = request.getParameter("company");
		if(company == null || company.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		List<Product> list = ProductService.selectByCompany(company);
		request.setAttribute("list", list);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ȸ�纰�� ��ǰ ����");
		return mv;
	}
}
