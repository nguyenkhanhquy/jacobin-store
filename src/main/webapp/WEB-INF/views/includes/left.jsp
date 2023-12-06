<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-lg-3 mb-2">
    <div class="list-group">
    	<a href="category" class="list-group-item list-group-item-dark">Tất cả danh mục</a>
        <c:forEach items="${ListC}" var="c">
            <a href="category?cId=${c.categoryId}" class="list-group-item list-group-item-action ${tag == c.categoryId ? 'active':''}">${c.name}</a>
        </c:forEach>
    </div>
</div>
