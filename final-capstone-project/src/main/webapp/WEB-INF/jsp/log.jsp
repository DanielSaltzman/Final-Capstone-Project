<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<c:url var="pulseLogo" value="img/electricity.png" />
	<img src="${pulseLogo}" height="40" width="40">
	<c:url var="surveyPage" value="/survey" />
	<a class="navbar-brand" href="${surveyPage}">Pulse Survey</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
			</ul>
			<div class="btn-group">
				<button type="button" class="btn btn-primary dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<c:url var="userLogo" value="img/icons/man.png" />
					<img src="${userLogo}" height="40" width="40">
					<c:out value="${currentUser.userName}"></c:out>
				</button>
				<div class="dropdown-menu dropdown-menu-right ml-auto mr-1">
					<c:set var="adminCheck" value="${currentUser.role}" />
					<c:if test="${adminCheck == 'Admin'}">
						<c:url var="userViewURL" value="userView" />
						<form action="${userViewURL}" method="GET">
							<button class="dropdown-item" type="submit">View Users</button>
						</form>
						<c:url var="logViewURL" value="log" />
						<form action="${logViewURL}" method="GET">
						<button class="dropdown-item" type="button">View Log</button>
						</form>
					</c:if>
					<button class="dropdown-item" type="submit" data-toggle="modal"
						data-target="#changePasswordModal">Change Password</button>

					<c:url var="logoutURL" value="logout" />
					<form action="${logoutURL}" method="POST">
						<button class="dropdown-item" type="submit">Logout</button>
					</form>
				</div>
			</div>
		</div>
</nav>

<ul class="list-group">
  <li class="list-group-item">Cras justo odio</li>
  <li class="list-group-item">Dapibus ac facilisis in</li>
  <li class="list-group-item">Morbi leo risus</li>
  <li class="list-group-item">Porta ac consectetur ac</li>
  <li class="list-group-item">Vestibulum at eros</li>
</ul>


<c:import url="/WEB-INF/jsp/footer.jsp" />