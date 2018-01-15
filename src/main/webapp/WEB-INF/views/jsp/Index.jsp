<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCSS" />
<spring:url value="/resources/core/css/style.css" var="styleCSS" />
<spring:url value="/resources/core/css/font-awesome.min.css"
	var="fontAwesomeCSS" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Server Side Search Using DataTables API</title>
<link rel="stylesheet" href="${bootstrapCSS}" />
<link rel="stylesheet" href="${styleCSS}" />
<link rel="stylesheet" href="${fontAwesomeCSS}" />
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<div class="row">
				<div class="table-responsive" style="font-size: 13px !important">
					<table id="projectTable"
						class="table table-hover table-striped table-bordered"
						cellspacing="0" width="100%" style="font-size: 13px !important">
						<thead>
							<tr id="headSearch"></tr>
							<tr>
								<th>CITY</th>
								<th>SHORT_STATE</th>
								<th>FULL_STATE</th>
								<th>COUNTY</th>
								<th>CITY_ALIAS</th>
							</tr>
						</thead>
					</table>
				</div>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<spring:url value="/resources/core/js/bootstrap.min.js"
	var="bootstrapJS" />
<spring:url value="/resources/core/js/custom.js" var="customJS" />
<script src="${bootstrapJS}"></script>
<script src="${customJS}"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
</html>
