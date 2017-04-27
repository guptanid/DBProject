<!DOCTYPE html>
<html>
<head>
	<title></title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="listevent.css">
</head>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<body>
<h1>List of Events</h1>
<div class="container">
<div class="jumbotron">
<table class="table table-striped">
  <thead>
  <tr>
  <th>Event Name</th>
  <th>Location</th>
  <th>Department</th>
  <th> </th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="eventList" items="${Event}">
  	<tr>
  		<td>${Event.Event}</td>
  		<td>${Event.Location}</td>
  		<td>${Event.Dep}</td>
  		<td> 
  	</tr>
  	</c:forEach>
  </tbody>
</table>
</div>
</div>
/event?id=all



<script type="text/javascript"
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>