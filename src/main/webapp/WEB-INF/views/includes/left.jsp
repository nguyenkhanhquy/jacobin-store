<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-lg-3">
    <div class="list-group ">
        <c:forEach items="${ListC}" var="c">
            <a href="#" class="list-group-item list-group-item-action">${c.name}</a>
        </c:forEach>
    </div>
</div>
