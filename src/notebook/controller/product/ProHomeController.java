package notebook.controller.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Product;

public class ProHomeController implements Controller {

	/**
	 * ȭ�� �����ϱ� ���� Controller
	 * Ȩ�������� �Ż�ǰ 3��, ����Ʈ ��ǰ 3��, �ƹ� ��ǰ 3�� ��� �Ѹ���
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Product> newProducts = new ArrayList<Product>();
		List<Product> bestProducts = new ArrayList<Product>();
		List<Product> randomProducts = new ArrayList<Product>();
		
		return null;
	}

}
