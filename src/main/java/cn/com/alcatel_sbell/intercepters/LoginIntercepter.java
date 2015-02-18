package cn.com.alcatel_sbell.intercepters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter implements HandlerInterceptor {
	private static final String[] IGNORE_URI = { "/login.", "loginpage",
			"backui/", "frontui/", "public/" };

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (request.getSession().getAttribute("REMOTE_USER") != null
				&& !"".equals(request.getSession().getAttribute("REMOTE_USER"))) {
			System.out.println("REMOTE_USER=>"
					+ request.getSession().getAttribute("REMOTE_USER"));
			return true;
		}
		boolean flag = false;
		String url = request.getRequestURL().toString();
		System.out.println(">>>: " + url);
		for (String s : IGNORE_URI) {
			if (url.indexOf(s) != -1) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			request.getSession().setAttribute("referencurl", url);
//			response.sendRedirect("/loginpage.action");
			response.sendRedirect(request.getContextPath().length()==1?request.getContextPath():request.getContextPath()+"/loginpage.action");

			// T_supplier_user user = LoginController.getLoginUser(request);
			// if (user != null) flag = true;
			return false;
		} else {

			return true;
		}

	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// response.sendRedirect("login.action");

	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
}
