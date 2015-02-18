<%@ include file="common/header.jsp" %>
<title>${msg}</title>
<link href="<%=base %>public/js/node_modules/uploadify/uploadify.css"  rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<%=base %>public/js/node_modules/bootstrap/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=base %>public/js/node_modules/bootstrap-switch/bootstrap-switch.min.css"/>
<link rel="stylesheet" href="<%=base %>public/css/index.css"/>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
   <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <h2><fmt:message key="projectname"></fmt:message></h2>
        </div>
        <div id="navbar" class="navbar-collapse">
          <form class="navbar-form navbar-right" action="loginout.action">
                <button id="loginout" curuser="<%=session.getAttribute("REMOTE_USER")%>" type="submit" class="btn btn-success"><fmt:message key="logout"/></button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>welcome <%=session.getAttribute("REMOTE_USER")%></h1>
        <p><fmt:message key="intro_index"/></p>
      </div>
    </div>
    <div class="container" id="container">
      <!-- Example row of columns -->
      <div class="row">
        <div  class="col-md-4 collapsed" id="querydiv" data-parent="#container"  aria-expanded="false" data-toggle="collapse"  href="#querykey" aria-controls="querykey">
          <h2><fmt:message key="query_index"/></h2>
          <p><fmt:message key="intro_query_index"/></p>
          <p><a role="button" href="#" class="btn btn-default">View details >></a></p>
        </div>
        <div  class="col-md-4 collapsed" id="uploaddiv" data-parent="#container" aria-expanded="false"  data-toggle="collapse"    href="#uploadfile"  aria-controls="uploadfile"
        >
          <h2><fmt:message key="upload_index"/></h2>
          <p><fmt:message key="intro_uplaod_index"/></p>
          <p><a role="button" href="#" class="btn btn-default">View details >></a></p>
       </div>
       	<div   class="col-md-4 collapsed" id="orgdiv" data-parent="#container" aria-expanded="false"  data-toggle="collapse"    href="#haveindex"  aria-controls="haveindex">
          <h2><fmt:message key="org_index"/></h2>
          <p><fmt:message key="intro_org_index"/></p>
          <p><a role="button" href="#" class="btn btn-default">View details >></a></p>
        </div>
      </div>

      <hr>


    </div> <!-- /container -->
    <div class="container">
    	<DIV ID="uploadfile" class="collapse"   role="tabpanel">
    		<div class="row">
				<div class="col-md-2" >
					<input type="file" name="upload" id="upload"  />
					<input type="hidden" name="jseesionid" id="jseesionid" value="${pageContext.session.id }"/>
				</div>
				<div class="col-md-3" >
						<a href="javascript:$('#upload').uploadify('upload', '*')" class="handleall btn btn-success  disabled" role="button"	>UPLOAD ALL</a>
						<a href="javascript:$('#upload').uploadify('cancel', '*')" class="handleall btn btn-info  disabled" role="button">	CANCEL ALL</a>
				</div>
				 	 <div class="checkbox col-md-3" data-toggle="tooltip" data-placement="top" title="select for public  otherwise for private" >
   					 	<label>
     				 			<input id="holder" type="checkbox" checked  > Index For Public
    					</label>
  						</div>
			</div>

			<div class="row">
				<div id="fileQueue"></div>
			</div>
			<div class="row">
				<div id="tips" class="col-md-12" ></div>
			</div>
					<div id="sellmd"></div>	
		</DIV>
		<div  id="querykey"  class="collapse"   role="tabpanel">
	
  				<div class="row">
  					<div class="form-group col-md-4">
   					 	<label class="sr-only" for="key">Email address</label>
  					  <input type="text" class="form-control" id="key" placeholder="Enter Key Words For Search"/>
  					</div>
					<div class="checkbox col-md-2" data-toggle="tooltip" data-placement="top" title="select for public  otherwise for 			private" >
   						 <label>
     					 <input id="queryholder" type="checkbox" checked  > Index For Public
    				</label>
  					</div>
  					<button type="submit"  id="submitquery" class="btn btn-default">Search</button>
  					</div>
  					<div class="row">
  						<div id="result"> </div>

  					</div>
			
		</div>
			
	 	<div id="haveindex"   class="collapse row"   role="tabpanel">
			
		</div>
	</div>	
	<div class="container">
		<hr/>
		<footer>
		        <p>&copy; Company 2014</p>
		 </footer>
 </div>
<div class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="error-titile"></h4>
      </div>
      <div class="modal-body">
        <p id="error-content"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="<%=base %>public/js/node_modules/jquery/dist/jquery-1.11.2.min.js"></script>
<script src="<%=base %>public/js/node_modules/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=base %>public/js/node_modules/jquery-i18n-properties-master/jquery.i18n.properties.js"></script>
<script src="<%=base %>public/js/node_modules/uploadify/jquery.uploadify.min.js"></script>
<script src="<%=base %>public/js/node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=base %>public/js/node_modules/bootstrap-switch/bootstrap-switch.min.js"></script>
<script src="<%=base %>public/js/query.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
   <script src="<%=base %>public/js/node_modules/bootstrap/html5shiv.min.js"></script>
  <script src="<%=base %>public/js/node_modules/bootstrap/response.js"></script>
<![endif]-->
<%@ include file="common/footer.jsp" %>