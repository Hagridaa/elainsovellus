<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Eläinlista</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />

</head>
<body>

<p><c:out value="${param.nimi}" /></p>
	<h1>Eläimet</h1>
	<a href="lisaa-elain">Lisää eläin</a>
	<table class="table table_striped" >
		<tr>
			
			<th>Nimi</th>
			<th>Laji</th>
			<th>Kuvaus</th>
			<th>Hinta</th>
		</tr>
		<c:forEach items="${elaimet}" var="elaimet">
			
			<tr>
				  <!-- Muokkaa
WebContent\view-kansion elainlista.jsp:tä niin, että jokaisella elainlistan rivillä on linkki, jossa kutsutaan
elainn poistavaa servlettiä. Kutsussa on myös parametrinä poistettavan elainn id. -->

				<td><c:out value="${elaimet.laji}" /></td> 
				<td><c:out value="${elaimet.nimi}" /></td>
				<td><c:out value="${elaimet.kuvaus}" /></td> 
				<td><c:out value="${elaimet.hinta}" /></td>
				
				<td><a href="poista-elain?id=<c:out value="${elaimet.id}" />">Poista</a></td>
				
				
			</tr>
		</c:forEach>
	</table>
	
<a href="/elainkauppa_kristiina">Takaisin etusivulle</a>
</body>
</html>