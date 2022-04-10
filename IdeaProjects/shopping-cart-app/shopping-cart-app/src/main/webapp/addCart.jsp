<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add to Cart</title>
        <style>
          .error
          {
            color: #ff0000;
            font-weight: bold;
          }
          </style>
    </head>
    <body>
        <h3><spring:message code="label.addToCart" text="Welcome, Enter The Product Details" /></h3>
        <form:form method="POST" action="/cart/add" modelAttribute="product">
        <br/>
             <table>
                <tr>
                    <td><spring:message code="label.productId" text="Product Id" /></td>
                    <td><form:input path="productId"/></td>
                    <td><form:errors path="productId" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><spring:message code="label.productName" text="Product Name" /></td>
                    <td><form:errors path="name" cssClass="error" /></td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.price" text="Price" /></td>\
                    <td><form:input path="price"/></td>
                    <td><form:errors path="price" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><spring:message code="label.quantity" text="Quantity" /></td>
                    <td><form:input path="quantity"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.book" text="Book" /></td>
                </tr>
                <tr>
                    <td>
                        <spring:message code="label.genre" text="Genre" />
                        </td>
                    <td><form:input path="genre"/></td>
                </tr>
                <tr>
                    <td>
                    <spring:message code="label.author" text="Author" />
                    </td>
                    <td><form:input path="author"/></td>
                </tr>
                <tr>
                    <td>
                    <spring:message code="label.publication" text="Publication" />
                    </td>
                    <td><form:input path="publications"/></td>
                </tr>
                <tr>
                    <td><spring:message code="label.apparel" text="Apparel" /></td>
                </tr>
                <tr>
                    <td>
                    <spring:message code="label.type" text="Type" />
                    </td>
                    <td><form:input path="type"/></td>
                </tr>
                <tr>
                    <td>
                    <spring:message code="label.brand" text="Brand" />
                    </td>
                    <td><form:input path="brand"/></td>
                </tr>
                <tr>
                    <td>
                    <spring:message code="label.design" text="Design" />
                    </td>
                    <td><form:input path="design"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add to Cart"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>