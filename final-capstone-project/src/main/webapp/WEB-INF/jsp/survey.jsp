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
				<c:set var="adminCheck" value="${currentUser.role}"/>
				<c:if test="${adminCheck == 'Admin'}">
					<c:url var="userViewURL" value="userView" />
					<form action="${userViewURL}" method="GET">
						<button class="dropdown-item" type="submit">View Users</button>
					</form>
					<button class="dropdown-item" type="button">View Log</button>
					</c:if>
					
					<c:url var="logoutURL" value="logout" />
					<form action="${logoutURL}" method="POST">
						<button class="dropdown-item" type="submit">Logout</button>
					</form>
				</div>
			</div>
		</div>
</nav>

<div class="list-group surveyView">
	<c:forEach var="survey" items="${surveys}">

		<c:url value="/surveyDetails" var="surveyDetailsURL">
			<c:param name="surveyId" value="${survey.surveyId}" />
		</c:url>

		<div class="list-group-item">
			<div class="d-flex w-100 justify-content-between">
				<h5 class="mb-1">
					<c:out value="${survey.surveyName}"></c:out>
				</h5>
				<small><a href="${surveyDetailsURL}"><button
							type="button" class="btn btn-primary">View</button></a></small>
			</div>
			<p class="mb-1">
				<c:out value="Class Room: ${survey.room}"></c:out>
			</p>
			<small><c:out value="${survey.createdDate}"></c:out></small>
		</div>
	</c:forEach>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-light btn-lg btn-block"
		data-toggle="modal" data-target="#exampleModal">Upload Survey
	</button>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Input Info</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="<c:url value="uploadFile"/>" method="POST"
					enctype="multipart/form-data">
					<label>Location:</label> <select class="form-control"
						name="location">
						<option value="Columbus">Columbus</option>
						<option value="Cleveland">Cleveland</option>
						<option value="Detroit">Detroit</option>
						<option value="Pittsburgh">Pittsburgh</option>
						<option value="Cincinnati">Cincinnati</option>
					</select> <br /> <label>Cohort Number:</label> <select class="form-control"
						name="cohortNumber">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select> <br /> <input type="text" class="form-control"
						placeholder="Instructor" name="instructor"> <br /> <input
						type="text" class="form-control" placeholder="Topic" name="topic">
					<br />
					<div class="form-group">
						<label for="exampleFormControlFile1">CSV File:</label> <input
							type="file" class="form-control-file"
							id="exampleFormControlFile1" name="file">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save
							changes</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>



<c:import url="/WEB-INF/jsp/footer.jsp" />