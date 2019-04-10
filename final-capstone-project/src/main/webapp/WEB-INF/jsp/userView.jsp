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
					<img src="${userLogo}" height="40" width="40"> UserName
				</button>
				<div class="dropdown-menu dropdown-menu-right ml-auto mr-1">
					<c:url var="userViewURL" value="userView" />
					<form action="${userViewURL}" method="POST">
						<button class="dropdown-item" type="submit">View Users</button>
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

<section class="userSection">
	<h1>Add User</h1>
	</br>
		<form id="textLeft" action="${userViewURL}" method="POST">
			<div class="form-group">
				<label for="exampleInputEmail1">Username</label>
				<input type="text" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Enter Username" name="userName">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label>
				<input type="password" class="form-control"
					id="exampleInputPassword1" placeholder="Password" name="password">
			</div>
			<p>Role</p>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="role" id="inlineRadio1" value="Admin">
				<label class="form-check-label" for="inlineRadio1">Admin</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="role" id="inlineRadio2" value="User">
				<label class="form-check-label" for="inlineRadio2">Normal
					User</label>
			</div>
			</br>
			</br>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
</section>




<c:import url="/WEB-INF/jsp/footer.jsp" />