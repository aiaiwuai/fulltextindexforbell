/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.25
 * Generated at: 2015-03-06 01:55:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1424140474925L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1425434772265L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
 String base=request.getContextPath().length()==1?request.getContextPath():request.getContextPath()+"/"; 
      out.write("\r\n");
      out.write("<base href=\"");
      out.print(base );
      out.write('"');
      out.write('/');
      out.write('>');
      out.write("\r\n");
      out.write("<title>Login</title>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("body,td,th {\r\n");
      out.write("\tfont-size: 14px;\r\n");
      out.write("}\r\n");
      out.write(".btn\r\n");
      out.write("{ \r\n");
      out.write("width:80px;\r\n");
      out.write("height:20px;\r\n");
      out.write("color:#111111;\r\n");
      out.write("\r\n");
      out.write("padding:0 0 2px 0 !important;\r\n");
      out.write("padding:2px 0 0 0 ;\r\n");
      out.write("background:url(http://wlsweb.cn.alcatel-lucent.com/weblib/images/80_20bg.jpg);\r\n");
      out.write("border:0;\r\n");
      out.write("\r\n");
      out.write("cursor:pointer;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</style></head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t\t\t<br />\r\n");
      out.write("\t\t\t<p align=\"center\" style=\"font-size:24px; font-weight:bold; font-family:Trebuchet MS; color:#0d4d8a\">&nbsp;</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<table width=\"1000\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" bgcolor=\"#ffffff\">\r\n");
      out.write("\t\t\t  <tr>\r\n");
      out.write("\t\t\t\t<td width=\"49%\"><img src=\"http://wlsweb.cn.alcatel-lucent.com/weblib/images/login_pic.jpg\" width=\"617\" height=\"383\" /></td>\r\n");
      out.write("\t\t\t\t<td width=\"51%\">\r\n");
      out.write("\t\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginerror}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("<br/>\r\n");
      out.write("\t\t\t\t<form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(base );
      out.write("login.action\" onsubmit=\"return chk()\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"0\" style=\"border:2px solid #CCCCCC\">\r\n");
      out.write("\t\t\t\t  <tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td height=\"33\" colspan=\"2\"\r\n");
      out.write("\t\t\t\t\t\t\t\tbackground=\"http://wlsweb.cn.alcatel-lucent.com/weblib/images/login_bg.gif\"><p\r\n");
      out.write("\t\t\t\t\t\t\t\t\talign=\"center\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstyle=\"color:#555555; margin:4px 0 8px 0; font-weight:bold;\">Please\r\n");
      out.write("\t\t\t\t\t\t\t\t\tuse your CSL and CIP to Login</p></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t  <tr>\r\n");
      out.write("\t\t\t\t\t<td width=\"28%\" height=\"10\"></td>\r\n");
      out.write("\t\t\t\t\t<td width=\"72%\"></td>\r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t  <tr>\r\n");
      out.write("\t\t\t\t\t<td height=\"45\">&nbsp;&nbsp;CSL : </td>\r\n");
      out.write("\t\t\t\t\t<td><input name=\"csl\" type=\"text\" class=\"itxt\" id=\"csl\" value=\"\" /></td>\r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t  <tr>\r\n");
      out.write("\t\t\t\t\t<td height=\"45\">&nbsp;&nbsp;CIP : </td>\r\n");
      out.write("\t\t\t\t\t<td><input name=\"password\" type=\"password\" class=\"itxt\" id=\"password\" /></td>\r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t  <tr>\r\n");
      out.write("\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t  <input align=\"absmiddle\" name=\"remember\" type=\"checkbox\" id=\"remember\" value=\"1\" />\r\n");
      out.write("\t\t\t\t\t  <label for=\"remember\">Remember</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t  <tr>\r\n");
      out.write("\t\t\t\t   \r\n");
      out.write("\t\t\t\t\t<td height=\"45\" colspan=\"2\"><div align=\"center\">\r\n");
      out.write("\t\t\t\t\t  <input name=\"Submit\" type=\"submit\" class=\"btn\" value=\"Login\" />\r\n");
      out.write("\t\t\t\t\t    <input name=\"referenceurl\" type=\"hidden\" value=\"");
      out.print(session.getAttribute("referencurl"));
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t  <input name=\"Submit2\" type=\"reset\" class=\"btn\" value=\"Reset\" />\r\n");
      out.write("\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\" align=\"center\"><input name=\"act\" type=\"hidden\" id=\"act\" value=\"login\" />\r\n");
      out.write("\t\t\t\t\t<input name=\"url\" type=\"hidden\" id=\"url\" value=\"/clamber/addsymols.php?\" />\r\n");
      out.write("\t\t\t\t\t<p>Any login problem please contact <a href=\"mailto:");
      if (_jspx_meth_fmt_005fmessage_005f0(_jspx_page_context))
        return;
      out.write('"');
      out.write('>');
      if (_jspx_meth_fmt_005fmessage_005f1(_jspx_page_context))
        return;
      out.write("</a></p>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t  </tr>\r\n");
      out.write("\t\t\t  <tr>\r\n");
      out.write("\t\t\t  <td colspan=\"2\" height=\"50\"></td>\r\n");
      out.write("\t\t\t  </tr>\r\n");
      out.write("\t\t\t  <tr>\r\n");
      out.write("\t\t\t  <td colspan=\"2\" height=\"2\" background=\"http://wlsweb.cn.alcatel-lucent.com/weblib/images/login_line.gif\"></td>\r\n");
      out.write("\t\t\t  </tr>\r\n");
      out.write("\t\t\t</table>\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<p style=\"font-family:Arial, Helvetica, sans-serif; font-size:13px;\" align=\"center\">Published &copy; 2010.12</p>\t\t\r\n");
      out.write("\t\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tfunction chk()\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tvar f = document.form1;\r\n");
      out.write("\t\t\t\tif(f.csl.value==\"\")\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\talert(\"Please input your csl.\");\r\n");
      out.write("\t\t\t\t\tf.csl.focus();\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(f.password.value==\"\")\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\talert(\"Please input your password.\");\r\n");
      out.write("\t\t\t\t\tf.password.focus();\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t</script>\t\r\n");
      out.write("\t\t\t<script src=\"");
      out.print(base );
      out.write("public/js/node_modules/jquery/dist/jquery-1.11.2.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("<script src=\"");
      out.print(base );
      out.write("public/js/node_modules/jquery/dist/jquery1.7.2.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(base );
      out.write("public/js/node_modules/theplugin/iealert.min.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(base );
      out.write("public/js/node_modules/theplugin/iealert/style.css\" />\r\n");
      out.write("   <script>\r\n");
      out.write("\t\t$(\"body\").iealert();\r\n");
      out.write("   </script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_fmt_005fmessage_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f0.setParent(null);
    // /WEB-INF/views/login.jsp(74,57) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f0.setKey("authoremail");
    int _jspx_eval_fmt_005fmessage_005f0 = _jspx_th_fmt_005fmessage_005f0.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f1 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f1.setParent(null);
    // /WEB-INF/views/login.jsp(74,91) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f1.setKey("authorname");
    int _jspx_eval_fmt_005fmessage_005f1 = _jspx_th_fmt_005fmessage_005f1.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f1);
    return false;
  }
}
