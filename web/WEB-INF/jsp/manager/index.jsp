<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/mystyle.css">-->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/mystyle.css" />">
        <title><spring:message code="index.title" /></title>
    </head>

    <body>
        <div style="margin:0 auto; width: 300px">
            <h3><spring:message code="index.main.header"/></h3>
            <h2><spring:message code="results.main.header" />:</h2>
            <table>
                <tr style="border: 1px solid"><th>OIDS</th><th>VALUE</th></tr>
                <c-rt:forEach items="${oids}" var="mibdata" varStatus="loopStatus"  >
                    
                    <tr  class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}"><td><c-rt:out value="${mibdata.key}" /></td><td><c-rt:out value="${mibdata.value}" /></td></tr>
                </c-rt:forEach>
            </table>

            <p><a href="index.htm"><spring:message code="results.link.form" /></a></p>
        </div>
    </body>
</html>
