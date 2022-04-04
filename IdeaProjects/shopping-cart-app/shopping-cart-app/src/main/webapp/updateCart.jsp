<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <h3>Updating the Cart, Enter The Product Details</h3>
        <form:form method="PUT"
          action="/cart/update" modelAttribute="product">
             <table>
                <tr>
                    <td><form:label path="productId">Product Id</form:label></td>
                    <td><form:input path="productId"/></td>
                </tr>
                <tr>
                    <td><form:label path="quantity">Quantity</form:label></td>
                    <td><form:input path="quantity"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>