<%-- 
    Document   : messageBar.jsp
    Created on : Jul 29, 2024, 2:32:00 PM
    Author     : robin
--%>

<div class="message-bar">
    <% 
        String successMessage = (String) request.getAttribute("successMessage");
        String errorMessage = (String) request.getAttribute("errorMessage");
        
        // ?????????????????????
        if (successMessage == null || successMessage.isEmpty()) {
            successMessage = request.getParameter("successMessage");
        }
        if (errorMessage == null || errorMessage.isEmpty()) {
            errorMessage = request.getParameter("errorMessage");
        }
        if (successMessage != null && !successMessage.isEmpty()) {
    %>
        <p class="success"><%= successMessage %></p>
    <% 
        } else if (errorMessage != null && !errorMessage.isEmpty()) {
    %>
        <p class="error"><%= errorMessage %></p>
    <% 
        }
    %>
</div>
<style>    
     .message-bar {
        border-radius: 4px; /* ???? */
        margin: 0 0; /* ????? */
        text-align: center; /* ?????? */
        font-size: 16px; /* ???? */
        width: 100%;
    }

    .success {
        background-color: #d4edda; /* ????? */
        color: #155724; /* ???? */
        border: 1px solid #c3e6cb; /* ???? */
        margin: 0 0; /* ????? */
    }

    .error {
        background-color: #f8d7da; /* ????? */
        color: #721c24; /* ???? */
        border: 1px solid #f5c6cb; /* ???? */
        margin: 0 0; /* ????? */
    }
</style>
