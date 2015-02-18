<%@ include file="common/header.jsp" %>
<title>Login</title>

<style type="text/css">

body,td,th {
	font-size: 14px;
}
.btn
{ 
width:80px;
height:20px;
color:#111111;

padding:0 0 2px 0 !important;
padding:2px 0 0 0 ;
background:url(http://wlsweb.cn.alcatel-lucent.com/weblib/images/80_20bg.jpg);
border:0;

cursor:pointer;
}

</style></head>

<body>
			<br />
			<p align="center" style="font-size:24px; font-weight:bold; font-family:Trebuchet MS; color:#0d4d8a">&nbsp;</p>
			
			<table width="1000" border="0" cellspacing="0" cellpadding="0" align="center" bgcolor="#ffffff">
			  <tr>
				<td width="49%"><img src="http://wlsweb.cn.alcatel-lucent.com/weblib/images/login_pic.jpg" width="617" height="383" /></td>
				<td width="51%">
					${loginerror}<br/>
				<form id="form1" name="form1" method="post" action="<%=base %>login.action" onsubmit="return chk()">
				
				<table width="95%" border="0" align="center" cellpadding="2" cellspacing="0" style="border:2px solid #CCCCCC">
				  <tr>
							<td height="33" colspan="2"
								background="http://wlsweb.cn.alcatel-lucent.com/weblib/images/login_bg.gif"><p
									align="center"
									style="color:#555555; margin:4px 0 8px 0; font-weight:bold;">Please
									use your CSL and CIP to Login</p></td>
						</tr>
				  <tr>
					<td width="28%" height="10"></td>
					<td width="72%"></td>
				  </tr>
				  <tr>
					<td height="45">&nbsp;&nbsp;CSL : </td>
					<td><input name="csl" type="text" class="itxt" id="csl" value="" /></td>
				  </tr>
				  <tr>
					<td height="45">&nbsp;&nbsp;CIP : </td>
					<td><input name="password" type="password" class="itxt" id="password" /></td>
				  </tr>
				  <tr>
					<td></td>
					<td>
					  <input align="absmiddle" name="remember" type="checkbox" id="remember" value="1" />
					  <label for="remember">Remember</label>
					</td>
				  </tr>
				  <tr>
				   
					<td height="45" colspan="2"><div align="center">
					  <input name="Submit" type="submit" class="btn" value="Login" />
					    <input name="referenceurl" type="hidden" value="<%=session.getAttribute("referencurl")%>" />
					  <input name="Submit2" type="reset" class="btn" value="Reset" />
					</div></td>
					</tr>
					<tr>
					<td colspan="2" align="center"><input name="act" type="hidden" id="act" value="login" />
					<input name="url" type="hidden" id="url" value="/clamber/addsymols.php?" />
					<p>Any login problem please contact <a href="mailto:<fmt:message key="authoremail"/>"><fmt:message key="authorname"/></a></p>
					</td>
				  </tr>
				</table>
				</form>
				
				</td>
			  </tr>
			  <tr>
			  <td colspan="2" height="50"></td>
			  </tr>
			  <tr>
			  <td colspan="2" height="2" background="http://wlsweb.cn.alcatel-lucent.com/weblib/images/login_line.gif"></td>
			  </tr>
			</table>	
		
		<p style="font-family:Arial, Helvetica, sans-serif; font-size:13px;" align="center">Published &copy; 2010.12</p>		
			<script type="text/javascript">
			function chk()
			{
				var f = document.form1;
				if(f.csl.value=="")
				{
					alert("Please input your csl.");
					f.csl.focus();
					return false;
				}
				if(f.password.value=="")
				{
					alert("Please input your password.");
					f.password.focus();
					return false;
				}
			}
			</script>	
			<script src="<%=base %>public/js/node_modules/jquery/dist/jquery-1.11.2.min.js"></script>
<%@ include file="common/footer.jsp" %>
