<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Event Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="event.css">
</head>

<body>
	<h1>Add Event</h1>
	<div class="container">
		<div class="jumbotron">
			<form class="form-horizontal" action="event" method="POST">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Event
						Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							placeholder="Event Name" name="eventName">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Location</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							placeholder="location" name="location">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Start
						Date</label>
					<div class="col-sm-10">
						<input type="date" class="form-control" id="inputEmail3"
							name="startDate">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">End
						Date</label>
					<div class="col-sm-10">
						<input type="Date" class="form-control" id="inputEmail3"
							name="endDate">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Start
						time</label>
					<div class="col-sm-10">
						<input type="time" class="form-control" id="inputEmail3"
							name="startTime">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">End
						time</label>
					<div class="col-sm-10">
						<input type="time" class="form-control" id="inputEmail3"
							name="endTime">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Department</label>
					<div class="col-sm-10">
						<div class="btn-group">
							<select name="department">
								<c:forEach items="${requestScope.departments }" var="d">
									<option value=${d.getId() }>${d.getName() }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Category</label>
					<div class="col-sm-10">
						<div class="btn-group">
							<select name="category">
								<c:forEach>
									<option value=${c.getId() }>${c.getName() }</option>
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

				<input id="submit_pos" class="btn btn-default" type="submit"
					value="Submit">
			</form>
		</div>


	</div>



	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>