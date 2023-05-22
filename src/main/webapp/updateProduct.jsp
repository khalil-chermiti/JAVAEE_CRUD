<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>

<div class="container-sm">
    <form action="updateProduct?id=${param.get("id")}" method="post" style="width: 300px ; margin: auto">
        <h1>update product</h1>

        <label>product title</label>
        <input type="text" name="title" class="form-control" value="${product.getTitle()}"/><br>

        <label>product price</label>
        <input type="text" name="price" class="form-control" value="${product.getPrice()}"/><br>

        <label>product quantity</label>
        <input type="text" name="quantity" class="form-control" value="${product.getQuantity()}"/><br>

        <input type="submit" value="add product" class="btn btn-primary mb-2 text-center"/>
    </form>
</div>
</body>
</html>
