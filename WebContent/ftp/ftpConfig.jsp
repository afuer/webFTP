<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/common/templates/main_template.jsp" pageTitle="ftp config">

<stripes:layout-component name="html_head">
<link href="${pageContext.request.contextPath}/css/jquery.contextMenu.css" rel="stylesheet" type="text/css" />
<style>

.tabs {
    width:90%;
    display:inline-block;
    margin-left:50px; 
    margin-top: 30px;
}
 
    /*----- Tab Links -----*/
    /* Clearfix */
    .tab-links:after {
        display:block;
        clear:both;
        content:'';
    }
 
    .tab-links li {
        margin:0px 5px;
        float:left;
        list-style:none;
       
    }
 
        .tab-links a {
            padding:9px 15px;
            display:inline-block;
            border-radius:3px 3px 0px 0px;
            background:#7FB5DA;
            font-size:16px;
            font-weight:600;
            color:#4c4c4c;
            transition:all linear 0.15s;
            text-decoration: none;
        }
 
        .tab-links a:hover {
            background:#a7cce5;
            text-decoration: underline;
        }
 
    li.active a, li.active a:hover {
        background:#fff;
        color:#4c4c4c;
    }
 
    /*----- Content of Tabs -----*/
    .tab-content {
        padding:15px;
        border-radius:3px;
        box-shadow:10px 10px 5px #888888;
        background:#088A08;
    }
 
        .tab {
            display:none;
        }
 
        .tab.active {
            display:block;
        }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-cron.js"></script>
<script type="text/javascript">
jQuery(document).ready(function() {
	
	
	$("#cronObj").cron({
		onChange: function() {
			//alert($(this).cron("value"));
			var cronxp = $(this).cron("value");
			//alert(cronxp);
			cronxp = cronxp.substring(0,cronxp.length-1);
			
	        $('#cronxpr').val(cronxp);
	    }

	});
	
	
	
	
	$("#ftp").addClass("Pageactive");
	
    jQuery('.tabs .tab-links a').on('click', function(e)  {
        var currentAttrValue = jQuery(this).attr('href');
 //alert(currentAttrValue);
        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).show().siblings().hide();
 
        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
 
        e.preventDefault();
    });
});


</script>
<script src="${pageContext.request.contextPath}/js/jquery.ui.position.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.contextMenu.js" type="text/javascript"></script>
</stripes:layout-component>
<stripes:layout-component name="leftMenu">
         <jsp:include page="left_sideTree.jsp"/>
</stripes:layout-component>
    <stripes:layout-component name="contents">



<form class="form" id="theForm" action="${pageContext.request.contextPath}/configSchedServ" method="post">

<div class="tabs">
 <ul class="tab-links">
        <li class="active"><a href="#tab1">Job details &gt;&gt; </a></li>
        <li><a href="#tab2">Source server &gt;&gt; </a></li>
        <li><a href="#tab3">Destination server &gt;&gt; </a></li>
        <li><a href="#tab4">Save Config &gt;&gt; </a></li>
    </ul>



 <div id="tabCont" class="tab-content">
        <div id="tab1" class="tab active">
	        <table>
	        	<tr>
		        	<td><label>Job Name :</label></td>
					<td><input type="text" name="jobName" id="jobName"></td>
				</tr>
				<tr>
					<td><label>scheduler run</label></td>
		        	<td><span id="cronObj"></span></td>
				</tr>
				
			</table>
			<input type="hidden" name="time" id="cronxpr"> 
			<input type="hidden" name="oldJob" id="oldJob">
        </div>
 
        <div id="tab2" class="tab">
        <table>
        <tr>
			<td><label>Source FTP server URL :</label></td>
			<td><input type="text" name="srcFTP" id="srcFTP"></td>
		</tr>
		<tr>
			<td><label>FTP Connection user name :</label></td>
			<td><input type="text" name="srcuser" id="srcuser"></td>
		</tr>	
		 <tr>
			<td><label>Password :</label></td>
			<td><input type="password" name="srcPass" id="srcPass"></td>
		</tr>	
		<tr>
			<td><label>File Path :</label></td>
			<td><input type="text" name="src" id="src"></td>
		</tr>
       
		<tr>	
				<td><label>Use secure Connection ?</label></td>
				<td><input  type="checkbox" name="issrcSFtpen" id="issrcSFtpen"/></td>
		</tr>
			
		<tr>	
			<td><label>Action required : </label></td>
			<td>
				<select name="postFTPAct" id="postFTPAct">
				  <option value="0">Copy</option>
				  <option value="1">Move</option>
				  <option value="2">Move with backup</option>
				</select>
			</td>
			<td><input type="text" style="display: none;" name="bkupDir" id="bkupDirTF" placeholder="Backup path"></td>
		</tr>
        </table>
        </div>
 
        <div id="tab3" class="tab">
        <table>
        	<tr>
				<td><label>Distination FTP server URL :</label></td>
				<td><input type="text" name="distFTP" id="distFTP"></td>
			</tr>
			<tr>
				<td><label>FTP Connection user name :</label></td>
				<td><input type="text" name="distuser" id="distuser"></td>
			</tr>	
			<tr>	
				<td><label>Password :</label></td>
				<td><input type="password" name="distPass" id="distPass"></td>
			</tr>		
			<tr>
				<td><label>File Path :</label></td>
				<td><input type="text" name="dist" id="dist"></td>
			</tr>
			
			<tr>	
				<td><label>Use secure Connection ?</label></td>
				<td><input  type="checkbox" name="isdstSFtpen" id="isdstSFtpen"/></td>
			</tr>
			
	    </table>    
        </div>
 
        <div id="tab4" class="tab">
        
        
        

		<input id="register" name="submit" type="submit" value="save and run"/>
		<input id="save" name="save" type="submit" value="save"/>
		<input id="updateBtn" name="update" type="submit" style="display: none;" value="update"/>
		<input id="updateNrunBtn" name="upNrun" type="submit" style="display: none;" value="update and run"/>
		<input id="configreset" name="reset" type="button" value="Reset"/>
        
        
        </div>
    </div>
</div>	











</form>

<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.dynatree.js" type="text/javascript"></script>


<link href="${pageContext.request.contextPath}/css/ui.dynatree.css" rel="stylesheet" type="text/css" id="skinSheet">
	
	<script  type="text/javascript">
	
	
	
	
	
	var pasteMode = null;
	 

	 
	function copyPaste(action, node) {
	 
	    switch( action ) {
	 
	    case "cut":
	 
	    case "copy":
	 
	        clipboardNode = node;
	 
	        pasteMode = action;
	 
	        break;
	 
	    case "paste":
	 
	        if( !clipboardNode ) {
	 
	            alert("Clipoard is empty.");
	 
	            break;
	 
	        }
	 
	        if( pasteMode == "cut" ) {
	 
	            // Cut mode: check for recursion and remove source
	 
	            var isRecursive = false;
	 
	            var cb = clipboardNode.toDict(true, function(dict){
	 
	                // If one of the source nodes is the target, we must not move
	 
	                if( dict.key == node.data.key )
	 
	                    isRecursive = true;
	 
	            });
	 
	            if( isRecursive ) {
	 
	                alert("Cannot move a node to a sub node.");
	 
	                return;
	 
	            }
	 
	            node.addChild(cb);
	 
	            clipboardNode.remove();
	 
	        } else {
	 
	            // Copy mode: prevent duplicate keys:
	 
	            var cb = clipboardNode.toDict(true, function(dict){
	 
	                dict.title = "Copy of " + dict.title;
	 
	                delete dict.key; // Remove key, so a new one will be created
	 
	            });
	 
	            node.addChild(cb);
	 
	        }
	 
	        clipboardNode = pasteMode = null;
	 
	        break;
	 
	    default:
	 
	        alert("Unhandled clipboard action '" + action + "'");
	 
	    }
	 
	};
	 

	
	
	
$(function () {
	
	$( "#postFTPAct" ).change(function() {
		//alert("changes and the value is :  "+$("#postFTPAct").val());
		if($("#postFTPAct").val()==2){
			
	        $('#bkupDirTF').show();
		}else{
			$('#bkupDirTF').hide();
		}
		});
	
	$('#configreset').click(function(){
		//alert("will reset");
        
		
		
		$("#jobName").val("");
						
										
						$("#cronObj").cron("value","* * * * *");

						$("#srcFTP").val("");
						$("#srcuser").val("");
						$("#src").val("");
						$("#srcPass").val("");
						
						$("#distFTP").val("");
						$("#distuser").val("");
						$("#dist").val("");
						$("#distPass").val("");
						
						
						
		$('#updateNrunBtn').hide();	            
        $('#updateBtn').hide();
        $('#register').show();
        $('#save').show();
        
        
        
        
        
        
       //var currentAttrValue = jQuery("a#tab4 ").attr('href');
        
       
       
       // Change/remove current tab to active
        jQuery(".tabs #tab1").parent('li').addClass('active');
        jQuery(".tabs #tab1").siblings().removeClass('active');
       
       
       
       
       
        // Show/Hide Tabs
        jQuery('.tabs #tab1').show();
        jQuery('.tabs #tab1').siblings().hide();
 
        
 //alert("ok");
      
        
});
	
	
	
	
	$("#tree").dynatree({
		initAjax: {
			url: "/shaah_FTP/LoadUserJobsServ",
				type:"post"
		},                      
		onActivate: function(node) {
			//alert(node.data);//$("#echoActive").text("" + node + " (" + node.getKeyPath()+ ")");
		},
		onLazyRead: function(node){
			
			node.appendAjax({
				url: "sample-data2.json",
				type:"post"
			});
		},onDblClick: function(node, event){
			
			runStart(node)
			
			
			
			
		}

		
	});
	
	
	$.contextMenu({
        selector: 'a.dynatree-title', 
        callback: function(key, options) {
            var m = "clicked: " + key;
            var node = $.ui.dynatree.getNode(this);
            
            console.log(m + " - " + node);
     

            //window.console && console.log(m) || alert(m); 
        },
        items: {
            "edit": {name: "Edit", icon: "edit", 
                callback: function(key, options) {
                    var m = "clicked: " + key;
                    var node = $.ui.dynatree.getNode(this);
                    
                     getJob(node);
             
					
                    //window.console && console.log(m) || alert(m); 
                }
        },            
            "sep1": "---------",
            "strtRstop": {name: "start / stop", icon: "quit", 
                callback: function(key, options) {
                    var m = "clicked: " + key;
                    var node = $.ui.dynatree.getNode(this);
                    
                    runStart(node);
             

                    //window.console && console.log(m) || alert(m); 
                }
        	}
        }
    });
    
    $('.context-menu-one').on('click', function(e){
        console.log('clicked', this);
    });
	
});


function runStart(node){
	
	var nodeSts = node.data.title;
	var nodestsArr= nodeSts.split("[");
	var curstatus = nodestsArr[1].substring(0, nodestsArr[1].length-1);
	var jobName = $.trim(nodestsArr[0]);
	var futstatus;
	
	if($.trim(curstatus)=="running"){
		
		futstatus= confirm("Are you sure you want to stop the job ?");
		if(futstatus==true){
			
			//alert("will stop the job");
			$.ajax({
					url:"/shaah_FTP/StopJobWizName",
					type:"POST",
					data:"jobName="+jobName,
					beforeSend:function(){
						node.setTitle(jobName+"[ configuring ]");
						//alert("ok");
					},
					success:function(data){
						node.setTitle(jobName+"[ stopped ]");
					}
			});
		}
	}else if($.trim(curstatus)=="configuring"){
		alert("cannot proceed with 2 operations for the same job !!");
	}else{
		
		futstatus= confirm("Are you sure you want to start the job ?");
		if(futstatus==true){
			
			//alert("will start the job");
			$.ajax({
				url:"/shaah_FTP/StartJobWizNameServ",
				type:"POST",
				data:"jobName="+jobName,
				beforeSend:function(){
					node.setTitle(jobName+"[ configuring ]");
					//alert("ok");
					
				},
				success:function(data){
					node.setTitle(jobName+"[ running ]");
				}
		});
		}
	}
}




function getJob(node){
	
	var nodeSts = node.data.title;
	var nodestsArr= nodeSts.split("[");	
	var jobName = $.trim(nodestsArr[0]);

			$.ajax({
					url:"/shaah_FTP/loadJobByName",
					type:"POST",
					data:"jobName="+jobName,
					
					success:function(data){
						var job = JSON.parse(data);
						
						$("#jobName").val(job.jobName.substring(2));
						$("#oldJob").val(job.oldJobName);
						var time = job.runTime;
						time = time.substring(2,time.length-1)+"*";						
						$("#cronObj").cron("value",time);

						$("#srcFTP").val(job.srcFtpServerURL);
						$("#srcuser").val(job.srcUserName);
						$("#src").val(job.source);
						$("#srcPass").val(job.srcPass);
						
						$("#distFTP").val(job.distFtpServerURL);
						$("#distuser").val(job.distUserName);
						$("#dist").val(job.dist);
						$("#distPass").val(job.distPass);
						$('#bkupDirTF').val(job.srcBckupDir);
						
						$("#postFTPAct").val(job.postFTPOpt);
						if(job.postFTPOpt==2){
							
							 $('#bkupDirTF').show();
						}
						
						if(job.srcFtpsEnbl){
							$('#issrcSFtpen').prop('checked', true);
						}
						
						
						if(job.distFtpsEnbl){
							$('#isdstSFtpen').prop('checked', true);
						}
						
						
						$('#updateNrunBtn').show();	
						$('#updateBtn').show();
			            $('#register').hide();
			            $('#save').hide();
						
					}
			});
		
			
}
	</script>
	

</stripes:layout-component>
</stripes:layout-render>