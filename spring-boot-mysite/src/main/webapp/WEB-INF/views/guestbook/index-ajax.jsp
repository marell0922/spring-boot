<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
	#dialog-delete-form p{
		padding : 10px;
		font-weight : bold;
		font-size : 1.0 em;
	}
	
	#dialog-delete-form input[type="password"]{
		padding:5px;
		outline: none;
		width : 100px;
	}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
//jquery plug-in
(function($){
	$.fn.hello =function(){
	console.log($(this));
	
}
})(jQuery);


var page = 0;
var isEnd=false;
var render = function(vo, mode){
	//현업에 가면 이렇게 안한다 =>template library [js]
	//ejs, underscore, mustache
	var htmls="<li data-no='"+vo.no+"'>" 
	+"<strong>"+vo.name+"</strong>"
	+"<p> "+vo.message.replace(/\n/g, "<br>")+"</p>"
	+"<strong></strong>"
	+"<a href='' data-no='"+vo.no+"'>삭제</a>"
	+"</li>";
	
	if(mode==true){
		$('#list-guestbook').prepend(htmls);
	}else{
	$('#list-guestbook').append(htmls);
	}
}

var fatchList = function(){
	if(isEnd==true){
		return;
	}
	++page;
	$.ajax({
		url : '/mysite4/guestbook/ajax/'+page ,
		type : 'get',
		dataType : 'json',
		data :'',
		success : function (response){
			//console.log(response);
			if(response.result=="fail"){
				console.warn(response.data);
				return;
			}
			
			//페이지의 끝 도출
			if(response.data.length <5){
				isEnd=true;
				$('#btn-next').prop("disabled", true);
			}
			
			//rendering
			// = $(response).each()
			$.each(response.data, function(index, vo){
				render(vo,false);
			})
		},
		error : function(xhr, status, e){
			console.error(status+" : "+e); // xhr : 브라우저 통신 객체
		}
	})
};

var messageBox=function(title, message){
	$('#dialog-message').attr("title", title);
	$('#dialog-message p').text(message);
	$('#dialog-message').dialog({
		modal : true,
		buttons : {
			"확인" :function(){
				console.log('......');
				$(this).dialog("close");
			}
		}
	});
	
}

$(function(){
	var dialogDelete= $('#dialog-delete-form').dialog({
		autoOpen : false,
		modal : true,
		buttons : {
			"삭제" : function(no){
				console.log("ajax 삭제 작업");
				//console.log($(this).data(no));
				//console.log( "checked : "+ $('#hidden-no').val());
				//$.ajax
				
				//form validate
				var no= $('#hidden-no').val();
				var passwordDelete= $('#password-delete').val();
				if(passwordDelete==""){
					$('#password-delete').attr("placeholder", "입력해주세요");
					$('#password-delete').focus();
					return;
				}
				//통신
				 $.ajax({
					url:"/mysite4/guestbook/delete",
					type:"post",
					dataType : "json",
					data :{
						"no" : no,
						"password" : passwordDelete
					} /* "a=ajax-delete&password="+passwordDelete */,
					success : function(response){
						//console.log(response);
						if(response.result=="fail"){
							console.log('delete fail');
							$('#dialog-delete-form p.error').css('display','');
							return;
						}
							console.log('삭제 성공');
							$('li[data-no='+no+']').remove();
							dialogDelete.dialog("close");
					},
					error : function(xhr, status, e){
						console.error(status + " : "+e);
					}
				})  
			},
			"취소" : function(){
				$(this).dialog("close");
			}
		},
		close:function() {
			console.log("close시 뒤처리...");
			$('#dialog-delete-form p.error').css('display','none');
			$('#password-delete').val("");
			
		}
	})
	//live event : 동적으로 미래에 생길 이벤트를 미리 설정 가능
	$(document).on("click",  '#list-guestbook li a', function(){
		event.preventDefault();
		console.log('clicked : '+$(this).data("no"));
		$('#hidden-no').val($(this).data("no"));
		//console.log($('#hidden-no').val())
		dialogDelete.dialog("open");
	} )
	//message 등록
	$('#add-form').submit(function(event){
		//submit의 기본동작 (post)
		//막아야 함 ( 페이지 이동이기에 페이지의 정보 값이 초기화된다.)
		
		event.preventDefault();
		
		//valiate form data
		var name=$('#input-name').val();
		if(name ==""){
			messageBox("글남기기", "이름은 필수 입력 항목입니다.");
			$('#input-name').focus();
			return;
		}
		
		var password = $('#input-password').val();
		if(password ==""){
			messageBox("글남기기","비밀번호는 필수 입력 항목입니다.");
			$('#input-password').focus();
			return;
		}
		
		var content=$('#tx-content').val();
		if(content ==""){
			messageBox("글남기기","내용은 필수 입력 항목입니다.");
			$('#tx-content').focus();
			return;
		}
		
		//통신
		$.ajax({
			url : "/mysite4/guestbook/ajax/insert",
			type:"post",
			dataType : "json",
			data :{
				"name" : name,
				"password": password,
				"content" : content
			} /* "a=ajax-add&name="+name+"&password="+password+"&content="+content */, 
			success :function(response){
				
				console.log(response.result);
				if(response.result=="fail"){
					console.warn(response.data);
					return;
				}
				
				console.log(response.no);
				render(response.vo, true);
				
				$('#input-name').val("");
				$('#input-password').val("");
				$('#tx-content').val("");
			},
			error : function(xhr, status, e){
				console.error(status + " : "+e);
			}
		})
	})
	
	//scroll event
	$(window).scroll(function(){
		var $window=$(this);
		var scrollTop=$window.scrollTop();
		var windowHeight=$window.height();
		var documentHeight=$(document).height();
		
		//console.log(scrollTop +" : "+ documentHeight+" : "+ windowHeight);
		if(scrollTop +windowHeight+10 > documentHeight){
			/* console.log('fetch ajax starting...'); */
			fatchList();
		}
	})
	
	$('#btn-next').click(function(){
		fatchList();
	})
	
	fatchList();
})
	
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" name="input-name" placeholder="이름">
					<input type="password" id="input-password" name="input-password" placeholder="비밀번호">
					<textarea id="tx-content" name="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">				
				</ul>
				<button id="btn-next">다음</button>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p style="padding : 30px"></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>