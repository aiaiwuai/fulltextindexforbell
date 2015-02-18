package cn.com.alcatel_sbell.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

public class I18N {
	public String getLocalMessage(HttpServletRequest request, String messageName) {
		ApplicationContext ctx = RequestContextUtils
				.getWebApplicationContext(request);
		Locale locale = RequestContextUtils.getLocale(request);
		return ctx.getMessage(messageName, null, locale);

	}

}
