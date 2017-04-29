<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>${requestScope.event.getDescription() }</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="event.css">
</head>

<body>
<h1>Update Event</h1>
 <div class="container">
 <div class="jumbotron">
<form class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Event Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="eventName" name="eventName" value=${requestScope.event.getDescription() }>
    </div>
	  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Location</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="eventLocation" name="eventLocation" value=${requestScope.event.getLocation() }>
    </div>
  </div>
    <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Start Date</label>
    <div class="col-sm-10">
      <input type="date" class="form-control" id="eventStartDate" name="eventStartDate" value=${requestScope.event.getStartDate() } >
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">End Date</label>
    <div class="col-sm-10">
      <input type="Date" class="form-control" id="eventEndDate" name="eventEndDate" value=${requestScope.event.getEndDate() }>
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Start time</label>
    <div class="col-sm-10">
      <input type="time" class="form-control" id="eventStartTime" name="eventStartTime" value=${requestScope.event.getStartTime() }>
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">End time</label>
    <div class="col-sm-10">
      <input type="time" class="form-control" id="eventEndTime" name="eventEndTime" value=${requestScope.event.getEndTime() }>
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Department</label>
    <div class="col-sm-10">
      <div class="btn-group">
  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Department Type <span class="caret"></span>
  </button>
  <select class="dropdown-menu" name="department">
   	<c:forEach items="${requestScope.departments }" var="d">
   		<option value=${d.getId() }>${ d.getName() }</option>
   	</c:forEach>
  </select>
</div>
    </div>
  </div>
   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Category</label>
    <div class="col-sm-10">
      <div class="btn-group">
  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Category Type <span class="caret"></span>
  </button>
  <select class="dropdown-menu" name="">
    <c:forEach items="${requestScope.categories }" var="c">
    	<option value="${c.getId() }">${c.getName() }</option>
    </c:forEach>
  </select>
</div>
    </div>
  </div>

   <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-10">
      <textarea class="form-control" rows="3"></textarea>
    </div>
  </div>

  <div class="row form-group">
<div class="col-sm-4"></div>
<div class="col-sm-2">
  <input id="update" class="btn btn-default " type="submit" value="Update">
  </div>
  <div class="col-sm-2">
  <input id="cancel" class="btn btn-default" type="button" value="cancel">
</div>
 </div>

 



  </div>
  

</div>



<script type="text/javascript"
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>