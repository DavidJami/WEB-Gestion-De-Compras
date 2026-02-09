<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url var="css" value="/assets/app.css"/>
<c:url var="urlHome" value="/"/>
<c:url var="urlProveedores" value="/proveedores"/>
<c:url var="urlBodegas" value="/bodegas"/>
<c:url var="urlIngresos" value="/ingresos"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Bodega</title>
    <link rel="stylesheet" href="${css}">
</head>
<body>
<div class="container">

    <div class="topbar">
        <h1>📦 Sistema de Gestión</h1>
        <div class="nav">
            <a class="btn" href="${urlHome}">Home</a>
            <a class="btn" href="${urlProveedores}">Proveedores</a>
            <a class="btn" href="${urlBodegas}">Bodegas</a>
            <a class="btn" href="${urlIngresos}">Ingresos</a>
        </div>
    </div>

    <div class="card">
        <div class="card-pad">
            <c:set var="isUpdate" value="${formAction == 'update'}"/>

            <h2>
                <c:choose>
                    <c:when test="${isUpdate}">✏️ Editar Bodega</c:when>
                    <c:otherwise>🏢 Nueva Bodega</c:otherwise>
                </c:choose>
            </h2>

            <form method="post" action="${urlBodegas}">
                <input type="hidden" name="action" value="${formAction}"/>

                <c:if test="${isUpdate}">
                    <input type="hidden" name="idBodega" value="${bodega.idBodega}"/>
                    <p class="muted">ID: <span class="id-pill"><c:out value="${bodega.idBodega}"/></span></p>
                </c:if>

                <div class="field">
                    <label>Nombre:</label>
                    <input type="text" name="nombre" value="${bodega.nombre}" maxlength="120" required/>
                </div>

                <div class="field">
                    <label>Ubicación:</label>
                    <input type="text" name="ubicacion" value="${bodega.ubicacion}" maxlength="200" required/>
                </div>

                <div class="actions">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                    <a class="btn" href="${urlBodegas}">Cancelar</a>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
