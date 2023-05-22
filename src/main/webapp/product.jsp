<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

    <style>
        @media (max-width: 576px) {
            td {
                width: 100% !important;
                display: block;
            }
        }

        .container-user {
            width: 50%;
            margin: 50px auto;
        }

        @media (max-width: 768px) {
            .container-user {
                width: 100%;
                margin: 50px auto;
            }
        }

    </style>
</head>
<body>

<div class="container container-user">
    <a href="logout" class="link-success">logout</a>
    <form action="addProduct" method="post" style="width: 300px ; margin: auto">
        <h1>add product</h1>

        <label>product title</label>
        <input type="text" name="title" class="form-control"/><br>

        <label>product price</label>
        <input type="text" name="price" class="form-control"/><br>

        <label>product quantity</label>
        <input type="text" name="quantity" class="form-control"/><br>

        <input type="submit" value="add product" class="btn btn-primary mb-2 text-center"/>

        <c:if test="${error != null}">
            <h3>${error}</h3>
        </c:if>

        <c:if test="${success != null}">
            <h3>${success}</h3>
        </c:if>

    </form>

    <hr>

    <h1>products</h1>
    <table class="table table-striped">
        <tr>
            <th>id</th>
            <th>title</th>
            <th>price</th>
            <th>quantity</th>
            <th>action</th>
        </tr>

        <c:if test="${products != null}">
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.title}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>
                        <a href="deleteProduct?id=${product.id}" class="link-danger">delete</a>
                        ||
                        <a href="updateProduct?id=${product.id}" class="link-warning">edit</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

    </table>
</div>


</body>
</html>
