<%@ page import="java.util.ArrayList" %>
<%@ page import="data.Offre" %>
<%@ page import="data.TypeO" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<%--<%
    ArrayList<Offre> offres = (ArrayList<Offre>) request.getAttribute("offres");
    ArrayList<TypeO> types = (ArrayList<TypeO>)request.getAttribute("Types");

%>--%>

<form method="post">
    Profile:
    <label>
        <input type="text" name="Profile">
    </label>
    Description:
    <label>
        <input type="text" name="Description">
    </label>
    <% List<TypeO> dd = (List<TypeO>) request.getAttribute("list");%>
    <select id="list" name="list">
    <%for (TypeO type : dd){%>
        <option value="<%= type.getIdType() %>"><%=type.getLibelle() %></option>
    <%}%>
    </select>
    <input type="submit" value="Add" name="Add">
</form>



<% List<Offre> dde = (List<Offre>) request.getAttribute("listOffres");%>
<%for (Offre type : dde){%>
<h2> <%=type.getProfile() %></h2>
<h2> <%=type.getDescription() %></h2>
<h2> <%=type.getTypeOByIdType().getLibelle() %></h2>
<%}%>
</body>
</html>