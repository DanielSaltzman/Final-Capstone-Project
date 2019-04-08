<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <c:url var="pulseLogo" value="img/electricity.png" />
  <img src="${pulseLogo}" height="40" width="40">
  <c:url var="loginPage" value="/login" />
  <a class="navbar-brand" href="${loginPage}">Pulse Survey</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
    </ul>
  </div>
</nav>

<c:url var="editLogo" value="img/icons/edit.png" />
<c:url var="trashLogo" value="img/icons/trash.png" />

<div class="list-group surveyView">
  <div class="list-group-item">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Survey #1</h5>
      <small><a href="#">Edit</a>  <img src="${editLogo}" height="15" width="15">  <a href="#">Trash</a>  <img src="${trashLogo}" height="15" width="15"></small>
    </div>
    <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
    <small>8/24/19</small>
  </div>
    <div class="list-group-item">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Survey #2</h5>
      <small><a href="#">Edit</a>  <img src="${editLogo}" height="15" width="15">  <a href="#">Trash</a>  <img src="${trashLogo}" height="15" width="15"></small>
    </div>
    <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
    <small>8/24/19</small>
  </div>
    <div class="list-group-item">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Survey #3</h5>
      <small><a href="#">Edit</a>  <img src="${editLogo}" height="15" width="15">  <a href="#">Trash</a>  <img src="${trashLogo}" height="15" width="15"></small>
    </div>
    <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
    <small>8/24/19</small>
  </div>
    <div class="list-group-item">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Survey #4</h5>
      <small><a href="#">Edit</a>  <img src="${editLogo}" height="15" width="15">  <a href="#">Trash</a>  <img src="${trashLogo}" height="15" width="15"></small>
    </div>
    <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
    <small>8/24/19</small>
  </div>
    <div class="list-group-item">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Survey #5</h5>
      <small><a href="#">Edit</a>  <img src="${editLogo}" height="15" width="15">  <a href="#">Trash</a>  <img src="${trashLogo}" height="15" width="15"></small>
    </div>
    <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
    <small>8/24/19</small>
  </div>
    <div class="list-group-item">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Survey #6</h5>
      <small><a href="#">Edit</a>  <img src="${editLogo}" height="15" width="15">  <a href="#">Trash</a>  <img src="${trashLogo}" height="15" width="15"></small>
    </div>
    <p class="mb-1">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
    <small>8/24/19</small>
  </div>
</div>


<div>
	<h1 class="title" style="color:white">Upload Survey</h1>
</div>
<div class="uploadField">
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
  </div>
  <div class="custom-file">
    <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
  </div>
</div>
</div>
<c:import url="/WEB-INF/jsp/header.jsp" />