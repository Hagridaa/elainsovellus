<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Lisää eläin</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />

</head>
<body>
<!--  JSP sisältää html-lomakkeen, jossa on uuden Eläimen nimi-, laji,
kuvaus- ja hinta-syöttökentät sekä lähetyspainike. Lisää eläinlista.jsp:n html-linkki, 
jonka href-osoitteena
on lisaa-elain -servlet -->

	<h1>Lisää Eläin</h1>
		<form action="lisaa-elain" method="post">
			<table>
				<tr>
					<td>Nimi:</td>
					<td><input type="text" 
					value=""
					name="nimi" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Laji:</td>
					<td><input type="text" 
					value=""
					name="laji" size="50"  />
					</td>
				</tr>
				<tr>
					<td>Kuvaus:</td>
					<td><input type="text" 
					value=""
					name="kuvaus" size="50"  />
					</td>
				</tr>	
				<tr>
					<td>Hinta:</td>
					<td><input type="text" 
					value=""
					name="hinta" size="50"  />
				</td>
				</tr>
				<tr>
					<td><div class ="button"><a href="listaa-elaimet">Peruuta</a></div></td>
					<td>
						<input type="submit" name="submit-button" class="btn btn-success" value="Tallenna" />
					</td>
				</tr>	
			</table>
			</form>
</body>
</html>