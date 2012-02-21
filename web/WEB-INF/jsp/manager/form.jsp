<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="index.title" /></title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/mystyle.css" />" />
    </head>

    <body>
        <div id="container">
            <div>
                <h3><spring:message code="index.main.header"/></h3>
                <div>
                    <a href="?lang=en" >english</a> | <a href="?lang=fil" >filipino</a>
                    Current Locale : ${pageContext.response.locale}
                </div>
                <form:form method="POST" commandName="mibForm" >
                    <p id="mibs">
                        <form:select path="mib">
                            <form:option value="0">
                                <spring:message code="index.main.form.mib.menu" />
                            </form:option>
                            <form:options items="${mibs}" />
                        </form:select>
                    </p>
                    <p><spring:message code="index.main.form.mib.address" /></p><p><form:input path="address" /><span style="color:red">${error}</span></p>
                    <p><input type="submit" name="submit" value="<spring:message code="index.main.form.mib.submit" />" /></p>
                    </form:form>
            </div>
            <div>
                <h3><spring:message code="index.main.form.mib.upload" /></h3>
                <form method="post" enctype="multipart/form-data" action="upload_mib.htm">
                    <p><spring:message code="index.main.form.mib.upload.oid.label" /></p>
                    <p><input type="file" name="oid_file" title="<spring:message code="index.main.form.mib.uploader" />" /></p>
                    <p><input type="submit" name="submit" value="<spring:message code="index.main.form.mib.upload.button" />"></p>
                </form>
            </div>
        </div>
    </body>
</html>
