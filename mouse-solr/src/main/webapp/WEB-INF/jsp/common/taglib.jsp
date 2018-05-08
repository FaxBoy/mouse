<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page language="java" session="true" buffer="none" autoFlush="true"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="PATH" value="<%=request.getContextPath() %>"/>

<script type="text/javascript">

var PATH = "<%=request.getContextPath() %>";

</script>

<!-- Bootstrap -->
<link href="${PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="//at.alicdn.com/t/font_430132_gjb2t83b9tjll3di.css" rel="stylesheet">
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${PATH}/static/jquery/jquery-3.3.1.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${PATH}/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
