package cn.com.alcatel_sbell.fulltextindex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.alcatel_sbell.utils.I18N;
import cn.com.alcatel_sbell.utils.LdapUtils;

@Controller
@RequestMapping("")
public class LoginController {
	@RequestMapping("/loginpage")
	public ModelAndView loginpage(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		if (request.getSession().getAttribute("REMOTE_USER") != null
				&& !"".equals(request.getSession().getAttribute("REMOTE_USER"))) {
			System.out
					.println(request.getSession().getAttribute("REMOTE_USER"));

			mView.setViewName("index");
		} else {
			mView.setViewName("login");
		}
		return mView;
	}
	@RequestMapping("/loginout")
	public void loginout(HttpServletResponse response,HttpServletRequest request) throws IOException{
		request.getSession().setAttribute("REMOTE_USER", null);
		response.sendRedirect(request.getContextPath().length()==1?request.getContextPath():request.getContextPath()+"/loginpage.action");

	}

	@RequestMapping("/login")
	public ModelAndView login(
			@RequestParam(value = "referenceurl", required = false) String referenceurl,
			@RequestParam(value = "csl", required = true) String csl,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<String> havNoLdapList = new ArrayList<String>();// csl+":"+password
		havNoLdapList.add("a:a");
		havNoLdapList.add("wqs:wqs");
		// if (LdapUtils.cnauthorization(csl, password)
		// || LdapUtils.ldapcaauthorization(csl, password)
		// || havNoLdapList.contains(csl + ":" + password)) {
		if (havNoLdapList.contains(csl + ":" + password)
				|| LdapUtils.cnauthorization(csl, password)) {
			request.getSession().setAttribute("REMOTE_USER", csl);
			response.sendRedirect(request.getContextPath().length()==1?request.getContextPath():request.getContextPath()+"/index.action");

//			if (null == referenceurl || "".equals(referenceurl)
//					|| "null".equals(referenceurl)) {
//				response.sendRedirect(request.getContextPath().length()==1?request.getContextPath():request.getContextPath()+"/index.action");
//			} else {
//				response.sendRedirect(referenceurl);
//			}
			return null;
		} else {
			ModelAndView mView = new ModelAndView();
			mView.addObject("loginerror",
					new I18N().getLocalMessage(request, "loginfaliure"));
			mView.setViewName("login");
			System.out.println("falire");
			return mView;
		}
	}
}
