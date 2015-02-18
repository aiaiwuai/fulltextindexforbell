<%@ include file="common/header.jsp" %>
<title>FULL TEXT PREVIEW OF ${document.get("filename")}</title>
<link rel="stylesheet" href="<%=base %>public/js/node_modules/bootstrap/dist/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=base %>public/js/node_modules/bootstrap-switch/bootstrap-switch.min.css"/>
<link rel="stylesheet" href="<%=base %>public/css/index.css"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<div class="container">
<div class="panel panel-success">
  <!-- Default panel contents -->
  <div class="panel-heading">${document.get("filename")}</div>
  <div class="panel-body">
    <pre>${document.get("content")}</pre>
  </div>
</div>
</div>
<script src="<%=base %>public/js/node_modules/jquery/dist/jquery-1.11.2.min.js"></script>
<script src="<%=base %>public/js/node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=base %>public/js/node_modules/bootstrap-switch/bootstrap-switch.min.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
   <script src="<%=base %>public/js/node_modules/bootstrap/html5shiv.min.js"></script>
  <script src="<%=base %>public/js/node_modules/bootstrap/response.js"></script>
<![endif]-->
<%@ include file="common/footer.jsp" %>