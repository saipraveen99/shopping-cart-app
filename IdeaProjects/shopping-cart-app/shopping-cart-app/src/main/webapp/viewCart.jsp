<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<table border="2" width="70%" cellpadding="2">

<tr>
    <th>Quantity</th>
    <th>Total Amount</th>
</tr>

<tr>
    <td>${responseDTO.cart.quantity}</td>
    <td>${responseDTO.cart.total_Amount}</td>
</tr>

<%boolean checkout = true;%>
    <form:form method="POST"
          action="/checkout" modelAttribute="checkout">
      <table>
          <tr>
              <td><input type="hidden" name="checkout" value="<%=checkout%>"/></td>
          </tr>
          <tr>
              <td><input type="submit" value="Checkout"/></td>
          </tr>
      </table>
    </form:form>
</table>