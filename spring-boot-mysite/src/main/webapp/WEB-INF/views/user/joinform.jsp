<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script>
	var messageBox = function(title, message) {
		$('#dialog-message').attr("title", title);
		$('#dialog-message p').text(message);
		$('#dialog-message').dialog({
			modal : true,
			buttons : {
				"확인" : function() {
					console.log('......');
					$(this).dialog("close");
				}
			}
		});
		return false;

	};
	/* $(function() {
		$('#join-form').submit(function() {
			//1.name check
			if ($('#name').val() == "") {
				alert('이름은 필수 입력 항목입니다.');
				messageBox('회원가입', '이름');
				$('#name').focus();
				return false;
			}

			//2.email check
			if ($('#email').val() == "") {
				alert('이메일은 필수 입력 항목입니다.');
				messageBox('회원가입', '이름');
				$('#email').focus();
				return false;
			}

			//3.password check
			if ($("input[type='password']").val() == "") {
				alert('비밀번호는 필수 입력 항목입니다.');
				messageBox('회원가입', '이름');
				$("input[type='password']").focus();
				return false;
			}

			//4.약관동의
			if ($('#agree-prov').is(':checked') == false) {
				alert('약관 동의를 해야 합니다.');
				messageBox('회원가입', '이름');
				return false;
			}

			//5. 중복 체크 확인
			if ($('#img-checkemail').is(":visible") == false) {
				alert('이메일 중복 체크를 해야 합니다.');
				messageBox('회원가입', '이름');
				return false;
			}
		}); 

		$('#email').change(function() {
			$('#btn-checkemail').css({
				'display' : ""
			})

			$('#img-checkemail').css({
				'display' : "none"
			})
		})

		$('#btn-checkemail')
				.click(
						function() {
							var email = $('#email').val();

							if (email == "") {
								return;
							}

							$
									.ajax({
										url : "${pageContext.servletContext.contextPath }/api/user/checkemail?email="
												+ email,
										type : "get",
										dataType : "json",
										data : "",
										success : function(response) {

											if (response.result == "fail") {
												return;
											}
											console.log(response);
											if (response.exist == true) {
												messageBox('회원가입', '이름');
												alert('이미 존재하는 이메일입니다. 다른 이메일을 선택해주세요');
												$('#email').val("").focus();
												return false;
											}

											//사용가능한 이메일
											$('#btn-checkemail').css({
												'display' : "none"
											})

											$('#img-checkemail').css({
												'display' : ""
											})
										},
										error : function(xhr, status, e) {
											console.error(status + " : " + e);
										}

									})
						})
	}) */
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form 
				modelAttribute="userVo"
				id="join-form" name="joinForm" method="post"
					action="${pageContext.servletContext.contextPath }/user/join">

					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="${userVo.name }">
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
						<p style="text-align: left; color:red"></p>
							<strong>
								<spring:message 
								code='${errors.getFieldError( "name" ).codes[0]}'
								text="${errors.getFieldError( 'name' ).defaultMessage }"/>
								</strong>
						</c:if>
					</spring:hasBindErrors>

					<label class="block-label" for="email">이메일</label> <form:input
					path="email"
						id="email" name="email" type="text" value="${userVo.email }"/> 
						<img
						id="img-checkemail" style="width: 15px; display: none"
						src="${pageContext.servletContext.contextPath }/assets/images/check.png"
						alt="" /> <input id="btn-checkemail" type="button"
						value="id 중복체크">
						<p style="padding:0; margin:0; color:red; text-align: left;">
						<form:errors path="email"></form:errors>
						</p>
						 <label class="block-label">패스워드</label> <input
						name="password" type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="f"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="m">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">
					<div id="dialog-message" title="" style="display: none">
						<p style="padding: 30px"></p>
					</div>
				</form:form>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>