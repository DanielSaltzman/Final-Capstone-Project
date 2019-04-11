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
					<img src="${userLogo}" height="40" width="40"> <c:out value="${currentUser.userName}"></c:out>
				</button>
				<div class="dropdown-menu dropdown-menu-right ml-auto mr-1">
					<c:url var="userViewURL" value="userView" />
					<form action="${userViewURL}" method="GET">
						<button class="dropdown-item" type="button">View Users</button>
					</form>
					<button class="dropdown-item" type="button">View Log</button>
					<c:url var="logoutURL" value="logout" />
					<form action="${logoutURL}" method="POST">
						<button class="dropdown-item" type="submit">Logout</button>
					</form>
				</div>
			</div>
		</div>
</nav>

<section class="detailSection">
	<div class="flexBox">
		<div class="flexItem title">
			</br>
			<h1>
				<c:out value="${selectedSurvey.surveyName}"></c:out>
			</h1>
		</div>
		<div class="flexItem">
			<div style="display: in-line">
				<h4>
					<c:out value="Location: ${selectedSurvey.campus}"></c:out>
				</h4>
				<h4>
					<c:out value="Cohort Number: ${selectedSurvey.cohortNumber}"></c:out>
				</h4>
			</div>
			<div style="display: in-line">
				<h4>
					<c:out value="Instructor: ${selectedSurvey.instructor}"></c:out>
				</h4>
				<h4>
					<c:out value="Topic: ${selectedSurvey.topic}"></c:out>
				</h4>
			</div>
		</div>
	</div>
	</br>
	<div class="list-group">
		<c:forEach var="question" items="${questions}">
			<div class="list-group-item">
				<div class="d-flex w-100 justify-content-between">
					<h5 class="mb-1">
						<c:out value="${question.questionText}"></c:out>
					</h5>
					<small><a href="#" onclick="myFunction();">View</a> <img
						src="${cursorLogo}" height="15" width="15"></small>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
<c:import url="/WEB-INF/jsp/footer.jsp" />