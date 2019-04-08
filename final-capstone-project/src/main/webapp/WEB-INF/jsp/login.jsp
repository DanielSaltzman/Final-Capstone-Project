<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="container">
	<div class="row justify-content-center align-items-center"
		style="height: 100vh">
		<div class="col-4">
			<div class="card">
				<div class="card-body">
					<c:url var="pulseLogo" value="img/electricity.png" />
					<h1><img class="icon" src="${pulseLogo}" height="40" width="40">  Pulse Survey</h1>
					<form action="" autocomplete="off">
						<div class="form-group">
							<input type="text" class="form-control" name="username">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name="password">
						</div>
						<button type="button" id="sendlogin" class="btn btn-primary">login</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />