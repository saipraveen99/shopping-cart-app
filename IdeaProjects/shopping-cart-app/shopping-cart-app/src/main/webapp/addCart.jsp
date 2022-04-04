<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <h3>Welcome, Enter The Product Details</h3>
        <form:form method="POST"
          action="/cart/add" modelAttribute="product">
             <table>
                <tr>
                    <td><form:label path="productId">Product Id</form:label></td>
                    <td><form:input path="productId"/></td>
                </tr>
                <tr>
                    <td><form:label path="name">Product Name</form:label></td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td><form:label path="price">Price</form:label></td>
                    <td><form:input path="price"/></td>
                </tr>
                <tr>
                    <td><form:label path="quantity">Quantity</form:label></td>
                    <td><form:input path="quantity"/></td>
                </tr>
                <tr>
                    <td><form:label path="genre">Genre</form:label></td>
                    <td><form:input path="genre"/></td>
                </tr>
                <tr>
                    <td><form:label path="author">Author</form:label></td>
                    <td><form:input path="author"/></td>
                </tr>
                <tr>
                    <td><form:label path="publications">Publication</form:label></td>
                    <td><form:input path="publications"/></td>
                </tr>
                <tr>
                    <td><form:label path="type">Type</form:label></td>
                    <td><form:input path="type"/></td>
                </tr>
                <tr>
                    <td><form:label path="brand">Brand</form:label></td>
                    <td><form:input path="brand"/></td>
                </tr>
                <tr>
                    <td><form:label path="design">Design</form:label></td>
                    <td><form:input path="design"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add to Cart"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>