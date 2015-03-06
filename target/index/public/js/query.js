$(function() {
    var base = $("base").attr("href");

    $("#submitquery").on("click", function() {
        $.ajax({
            url: base + "search.action",
            type: "post",
            dataType: "json",
            data: {"key":$("#key").val(),"holder":getHolder("queryholder"),"length":$("#digistlength").val()},
            success: function(data) {
            	// originSrearchHandle(data);
            	tableSearchHandle(data);
}
        });
        return false;



    });
        $(document).on("click","#exporttoexcel", function() {
//         $.ajax({
//             url: base + "exporttoexcel.action",
//             type: "post",
//             async:false,
//             // dataType:"application/vnd.ms-excel",
//             data: {"key":$("#key").val(),"holder":getHolder("queryholder"),"length":$("#digistlength").val()},
//             success: function(data) {
//             	// originSrearchHandle(data);
//             	tableSearchHandle(data);
// }
//         });
//         // window.open(base+"exporttoexcel.action?key="+$("#key").val()+"&length="+$("#digistlength").val()+"&holder="+getHolder("queryholder"));
//         return false;
$("#extoe").submit();
  return false;

    });
    function tableSearchHandle(data){
    		      if (!$.isEmptyObject(data)) {
    		      	 var order=0;
                    var str = "<table class=\"table table-bordered\"><caption>"+data.length+" results,<a id='exporttoexcel' href='#'>Export Results to Excel</a></caption><thead>\
                    \<tr><th></th><th>FileName</th><th>Product name</th><th>Product release</th><th>Match content digist</th><th>Uploader</th><th>downloadlink</th></thead><tbody>";

                    $.each(data, function(i, n) {
                    	if(i=="length"){
                    		return;
                    	}
                        var deleteindex="";
                        var deletefile="";
                        var downloadfile="";

                        if(n.uploader==$("#loginout").attr("curuser")){
                               deleteindex='<button type="button" class="btn btn-danger deleteindex" key="'+i+'">DEL-INDEX</button>';
                            if(n.filePath!="" && n.filePath!=null){
                                     deletefile='<button type="button" key="'+i+'" class="btn btn-danger deletefile">DEL-FILE</button>';
                            }
                        }
                        if(n.filePath!="" && n.filePath!=null){
//                            downloadfile='<a href="'+base+'ajax/downloadfile.action?id='+i+'" type="button" key="'+i+'" class="btn btn-success downloadfile" >DOWN-FILE</a>';
                            downloadfile='<a href="file:'+n.filePath+'" type="button" key="'+i+'" class="btn btn-success downloadfile COPYTOCLIPBORAD" >DOWN-FILE</a>';
                        }
                    	var buttongroup='<div class="btn-group " role="group">'+deleteindex+downloadfile+deletefile+'</div>';
                    	var shortdigust=n.digist.substring(0,20);
                        str += "<tr><td>"+(++order)+"</td><td><a target='_blank' href=\""+base+"fulltext.action?id="+i+"\">" + n.filename + "</a></td><td></td><td></td><td>"+n.digist+"</td><td>"+n.uploader+"</td><td>"+buttongroup+"</td></tr>";
                    });
                    str+="</tbody></table>"
                   $("div#result").html(str);
                }else{
                	   $("div#result").html("There is no result for your words");
                }









    }
function originSrearchHandle(data){
  //               '<div class="panel panel-default">\
  // <div class="panel-heading">'+n.filename+    n.holder   +n.uploder+'</div>\
  // <div class="panel-body">'+ n.digest+'</div><div class="panel-footer">n.score\
  //    <a href="'+base+'ajax/downloadfile.action?id='+i+'"  key="'+i+'">DOWNLOAD</a> </div></div>'
                    var str = "<ul>";
                if (!$.isEmptyObject(data)) {
                    $.each(data, function(i, n) {
                        // str += "<li><div class='resultItem'>文件名:" + i + ",匹配度:" + n.score * 100 + "</br>";
                        // str += "摘要:" + n['digest'] + "</div>";
  //                      str +=  '<div class="panel panel-default">\
  // <div class="panel-heading"><span class="panelheader">FileName:'+n.filename+'</span><span class="panelheader"><a href="'+base+'ajax/downloadfile.action?id='+i+'">\
  //   DOWNLOAD</a></span><span clas="panelheader"><a target="_blank" href="'+base+'fulltext.action?id='+i+'">FullText</a></span></div>\
  // <div class="panel-body"><pre>'+ n.digist+'</pre></div><div class="panel-footer"><span class="panelfooter">Matching Score:'+n.matchscore*100+'</span>\
  //    <span class="panelfooter">Group:'+n.holder+'</span><span class="panelfooter">Uploder:'+n.uploader+'</span></div></div>'
     str +=  '<div class="panel panel-success">\
  <div class="panel-heading"><span class="panelheader">'+n.filename+'</span><span class="panelheader"><a class="COPYTOCLIPBORAD" href="file:'+n.filePath+'">\
    DOWNLOAD</a></span></div>\
  <div class="panel-body"><pre>'+ n.digist+'</pre></div><div class="panel-footer"><span class="panelfooter">Matching Score:'+n.matchscore*100+'</span>\
     <span class="panelfooter">Group:'+n.holder+'</span><span class="panelfooter">Uploder:'+n.uploader+'</span></div></div>'
                    });
                }else{

        str =  '<div class="panel panel-default"><div class="panel-heading">no result</div><div class="panel-body">Have no Match-Document!</div></div>'
                }
                   $("div#result").html(str);
                   $("div#result .panel:odd").removeClass("panel-default").addClass("panel-info");
            


}
    $(document).on("*","mouseover",function(){
    		if($(this).attr("data-toggle").indexOf("tooltip")>-1){
    			$(this).tooltip();
    		}
    });
     $(document).on("click",".COPYTOCLIPBORAD",function(){
   			newalert("For security please  type  Ctrl+C to copy  the file-path then open it in your file explorer!</br><textarea  class='patharea form-control' >"+$(this).attr("href")+"</textarea>","COPY THEN OPEN");
    	
    		$('.modal').on('shown.bs.modal', function (e) {
              $(".patharea").focus().select();
               $(document).keypress(function (e) {
               		if(e.which==99 &&  e.ctrlKey ){
        			$(".modal").modal('hide');
        		}
   			 });
            })
            return false;
    		   
    });

    $(".collapse").on("show.bs.collapse", function() {
        $(this).siblings(".collapse.in").collapse('hide');
        if ($(this).attr("id") == "haveindex") {
            list();
        }
        if($(this).attr("id")=="uploadfile" ){
        	// usehandleall();
        }
         if($(this).attr("id")=="querykey" ){
            if($("#key").val()=="")
            $("#result").html("");
            }
    });
    // function usehandleall(){
    // 	if($(".uploadify-queue-item").length==0 && !$(".handleall").hasClass("disabled")){
    //    			$(".handleall").addClass("disabled");
    //    		}else if(($(".uploadify-queue-item").length!=0 && $(".handleall").hasClass("disabled"))){
    //    			$(".handleall").removeClass("disabled");
    //    		}
    // }
    function newalert(item, titile) {
        $("#error-content").append(item);
        $("#error-titile").text(titile || "ERROR!");
        $(".modal").modal("show");
    }
    function  newalertaddtext (argument) {
    	 $("#error-content").append(argument);
    }
    // $("#upload").uploadify({
    //     'swf': base + "public/js/node_modules/uploadify/uploadify.swf",
    //     "uploader": base + "upload.action;jsessionid=" + $("#jseesionid").val(),
    //     //	           'script'         : 'scripts/uploadify',//servlet的路径或者.jsp 这是访问servlet 'scripts/uploadif'   
    //     'method': 'POST ', //如果要传参数，就必须改为GET  
    //     //	           'cancelImg'      : 'js/cancel.png',  
    //     //	           'folder'         : 'uploads', //要上传到的服务器路径，  
    //     'queueID': 'fileQueue',
    //     'removeTimeout' : 0,
    //     "multi": true,
    //     'buttonClass': 'btn',
    //     'buttonText': 'Click And Select Files',
    //     'auto': false, //选定文件后是否自动上传，默认false  
    //     //	           'multi'          : , //是否允许同时上传多文件，默认false    
    //     'sizeLimit': 104857600, //设置单个文件大小限制，单位为byte    
    //     'queueSizeLimit': 10, //限制在一次队列中的次数（可选定几个文件）。默认值= 999，而一次可传几个文件有 simUploadLimit属性决定。  
    //     //	           'fileDesc'       : '支持格式:jpg或gif', //如果配置了以下的'fileExt'属性，那么这个属性是必须的    
    //     //	           'fileExt'        : '*.jpg;*.gif',//允许的格式  
    //     "fileObjName": "upload",
    //     'uploadLimit': 10,
    //     'formData': {
    //         lastmodifieddate: "",
    //         lastmodifieddatestr: "",
    //         holder:""
    //     },
    //     'itemTemplate' : '<div id="${fileID}" class="uploadify-queue-item">\
    //     			<div class="original col-md-3">\
    //                 <div class="cancel">\
    //                     <a href="javascript:$(\'#${instanceID}\').uploadify(\'cancel\', \'${fileID}\')"><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>\
    //                 </div>\
    //                 <span class="fileName">${fileName} (${fileSize})</span><span class="data"></span>\
    //             	</div>\
    //             	<div class="lastmodifieddate col-md-3"></div>\
    //             	<div class="index col-md-2"></div>\
    //             	<div class="server col-md-3"></div>\
    //             	<div class="upl col-md-1"></div>\
    //             </div>' ,
    //     //	           "formData":{"S":"s"},
    //     'onQueueComplete' : function(queueData) {
    //          $("#error-titile").text("THE UPLOAD RESULT:");
    //          $(".modal").modal("show");
    //         $(".handleall").addClass("disabled");

    //     },
    //     'onUploadSuccess': function(file, data, response) {
    //         if (data != 1) {
    //             newalertaddtext("<span class='older'>"+data+"</span></br>");
    //         } else {
    //             newalertaddtext("<span class='newer'>The file '" + file.name + "'' has been successfully index</span>.</br>");
    //         }
    //     },
    //     'onCancel': function(file) {
    //     	setTimeout(usehandleall,2000);
    //         $("div#tips").append('<br/>The file ' + file.name + ' was cancelled.');
    //     },
    //      'onClearQueue' : function(queueItemCount) {
    //      	setTimeout(usehandleall,2000);
    //     } ,
    //     'onSelect': function(file, queueID) {
    //         // $("#upload").uploadify("cancel");
    //         $("#sellmd").html("");
    //          $("div#tips").html("");
    //         // if (!handlefilename(file.name)) {
    //         //     newalert("filename can't contain the text '&'!");
    //         //     $("#upload").uploadify("cancel", file.id);
    //         //     return;
    //         // }
    //         var sellmd = file.modificationdate.getTime();
    //         var sellmdstr = file.modificationdate;
    //         var filename = file.name;
  		// 	$(".handleall").removeClass("disabled");
    //         validataFile(filename, sellmdstr, sellmd,file);

    //     },
    //     'onUploadStart': function(file) {
    //         $("#upload").uploadify("settings", "formData", {
    //             "lastmodifieddate": file.modificationdate.getTime(),
    //             "lastmodifieddatestr": file.modificationdate,
    //             "holder":getHolder("holder")
    //         });


    //     }

    // });
function getHolder(holderid){
	if($("#"+holderid).prop("checked")==true){
                		return "public";
                	}else{
                		return "private";
                	}

}
$('.modal').on('hidden.bs.modal', function (e) {
  		$("#error-content").html("");
})
    // loadProperties('main', '/strings/main/');

    function handlefilename(filename) {
        if (filename.indexOf("&") != -1) {
            return false
        }
        return true;
    }
    $(".newtagurl").on("click",function(){
        window.open($(this).attr("href"));
        return false;
    })
    function validataFile(filename, sellmdstr, sellmd,file) {
        //$("#upload1").on("change",function(){

        //	var filename=this.files[0].name;
        //var sellmdstr=this.files[0].lastModifiedDate;
        //var sellmd=this.files[0].lastModified;
        console.log(this);
        var id=file.id;
        sellmdstr=sellmdstr.toString().replace("(China Standard Time)","");
        //		 var f = document.getElementById("upload").files;  
        //上次修改时间  

        //		    document.getElementById("lastmodifieddate").value=f[0].lastModifiedDate.getTime();  
        //		    document.getElementById("lastmodifieddatestr").value=f[0].lastModifiedDate;  
        //		    //名称  
        // $("#sellmd").append("selected file '" + filename + "''s  lastmodified date is :" + sellmdstr + "</br>");
        $.ajax({
            url: base + "ajax/querybyfilename.action?rand=" + new Date().getTime(),
            type: "post",
            dataType: "json",
            async: false,
            data: {"filename":filename,"holder":getHolder("holder")},
            success: function(data) {
                if ($.isEmptyObject(data)) {
                    $("div#"+id+" .index").html("Index-Sta:<span class='raw'>NONE!</span></br/>");
                    $("div#"+id+" .lastmodifieddate").html("<span class='raw'>L:"+sellmdstr+"</span>");
                    $("div#"+id+" .original .cancel a").addClass("newer");
                    $("div#"+id+" .upl").html("<a href=\"javascript:$('#upload').uploadify('upload', '"+id+"')\"><span class=\"raw glyphicon glyphicon-ok\" aria-hidden=\"true\"></span></script>");

                } else {
               		 if ((sellmd / 1000 - data.lastmodified / 1000) > 1) {
               		     $("div#"+id+" .index").html("Index-Sta:<span class='newer'>HAVE & OLDER!</span>");
               		     $("div#"+id+" .lastmodifieddate").html("<span class='newer'>L:"+sellmdstr+"</span>");
               		     $("div#"+id+" .server").html("<span class='older'>S:"+data.lastmodifiedstr+"</span>");
 						 $("div#"+id+" .original .cancel").addClass("newer");
                    $("div#"+id+" .upl").html("<a href=\"javascript:$('#upload').uploadify('upload', '"+id+"')\"><span class=\"newer glyphicon glyphicon-ok\" aria-hidden=\"true\"></span></script>");

               		 } else if ((sellmd / 1000 - data.lastmodified / 1000) < -1) {
               		     $("div#"+id+" .index").html("Index-Sta:<span class='older'>HAVE & NEWER!</span>");
               		     $("div#"+id+" .lastmodifieddate").html("<span class='older'>L:"+sellmdstr+"</span>");
               		     $("div#"+id+" .server").html("<span class='newer'>S:"+data.lastmodifiedstr+"</span>");

               		     $("div#"+id+" .original .cancel").addClass("same");
                    $("div#"+id+" .upl").html("<a href=\"javascript:$('#upload').uploadify('upload', '"+id+"')\"><span class=\"older glyphicon glyphicon-ok\" aria-hidden=\"true\"></span></script>");

               		 } else if ((sellmd / 1000 - data.lastmodified / 1000) > -1 && (sellmd / 1000 - data.lastmodified / 1000) < 1) {
               		     $("div#"+id+" .index").html("Index-Sta:<span class='same'>HAVE & SAME!</span>");
               		     $("div#"+id+" .lastmodifieddate").html("<span class='same'>L:"+sellmdstr+"</span>");
               		     $("div#"+id+" .server").html("<span class='same'>S:"+data.lastmodifiedstr+"</span>");
               		     $("div#"+id+" .original .cancel span").addClass("newer");
                         $("div#"+id+" .upl").html("<span class=\"same glyphicon glyphicon-ok\" aria-hidden=\"true\"></span>");
               		 }
                    

                }
               
            }

        });
        //});
    }
    $(document.body).on("click", "button.deleteindex", function() {
        var id = $(this).attr("key");
        var obj = this;
        $.ajax({
            url: base + "delete.action",
            data: {
                "id": id
            },
            type: "post",
              dataType:"json",
            success: function() {
                $(obj).parentsUntil("tbody").remove();
            }
        });
        return;
    });
    list();
  $(document.body).on("click", "button.deletefile", function() {
        var id = $(this).attr("key");
        var obj = this;
        $.ajax({
            url: base + "ajax/deletefile.action",
            data: {
                "id": id
            },
            type: "post",
            dataType:"json",
            success: function(data) {
                if(data[0].status==1){
                    $(obj).parentsUntil("tbody").find(".deletefile,.downloadfile").remove();
                }else{
                    newalert(data[0].error,"ERROR!")
                }
            }
        });
        return;
    });


    function list() {
        $.ajax({
            url: base + "ajax/list.action?random="+new Date().getTime(),
            type: "get",
            dataType: "json",
            success: function(data) {
                if (!$.isEmptyObject(data)) {
                    var str = "<table class=\"table table-bordered\"><caption>Indexed Files Info</caption><thead>\
                    \<tr><th>FileName</th><th>LastModifieddate</th><th>Hoder</th><th>Uploader</th><th>Operator</th></thead><tbody>";

                    $.each(data, function(i, n) {
                        var deleteindex="";
                        var deletefile="";
                        var downloadfile="";
                        if(n.uploader==$("#loginout").attr("curuser")){
                               deleteindex='<button type="button" class="btn btn-danger deleteindex" key="'+i+'">DEL-INDEX</button>';
                            if(n.filePath!="" && n.filePath!=null){
                                     deletefile='<button type="button" key="'+i+'" class="btn btn-danger deletefile">DEL-FILE</button>';
                            }
                        }
                        if(n.filePath!="" && n.filePath!=null){
//                            downloadfile='<a href="'+base+'ajax/downloadfile.action?id='+i+'" type="button" key="'+i+'" class="btn btn-success downloadfile" >DOWN-FILE</a>';
                            downloadfile='<a href="file:'+n.filePath+'" type="button" key="'+i+'" class="btn btn-success downloadfile" >DOWN-FILE</a>';
                            
                            
                        }
                    	var buttongroup='<div class="btn-group" role="group">'+deleteindex+downloadfile+deletefile+'</div>';
                        str += "<tr><td><a target='_blank' href=\""+base+"fulltext.action?id="+i+"\">" + n.filename + "</a></td><td>"+n.lastmodifieddatestr+"</td><td>"+n.holder+"</td><td>"+n.uploader+"</td><td>"+buttongroup+"</td></tr>";
                    });
                    str+="</tbody></table>"
                    $("#haveindex").html(str);
                }else{
                	   $("#haveindex").html("There is no index files on the server");
                }

            }


        });
    }
});
