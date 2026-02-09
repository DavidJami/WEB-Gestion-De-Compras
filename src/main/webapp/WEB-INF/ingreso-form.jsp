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
    <title>Ingreso</title>
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
                    <c:when test="${isUpdate}">✏️ Editar Ingreso</c:when>
                    <c:otherwise>📥 Nuevo Ingreso</c:otherwise>
                </c:choose>
            </h2>

            <form method="post" action="${urlIngresos}">
                <input type="hidden" name="action" value="${formAction}"/>

                <c:if test="${isUpdate}">
                    <input type="hidden" name="idIngreso" value="${ingreso.idIngreso}"/>
                    <p class="muted">ID: <span class="id-pill"><c:out value="${ingreso.idIngreso}"/></span></p>
                </c:if>

                <div class="row">
                    <div class="field">
                        <label>Fecha:</label>
                        <input type="date" name="fecha" value="${ingreso.fecha}" required/>
                    </div>
                    <div class="field">
                        <label>Cantidad:</label>
                        <input type="number" name="cantidad" value="${ingreso.cantidad}" min="1" required/>
                    </div>
                </div>

                <div class="field">
                    <label>Proveedor:</label>
                    <select name="idProveedor" required>
                        <c:forEach var="p" items="${proveedores}">
                            <option value="${p.idProveedor}"
                                <c:if test="${isUpdate && ingreso.idProveedor == p.idProveedor}">selected="selected"</c:if>>
                                <c:out value="${p.nombre}"/> - <c:out value="${p.ruc}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="field">
                    <label>Bodega:</label>
                    <select name="idBodega" required>
                        <c:forEach var="b" items="${bodegas}">
                            <option value="${b.idBodega}"
                                <c:if test="${isUpdate && ingreso.idBodega == b.idBodega}">selected="selected"</c:if>>
                                <c:out value="${b.nombre}"/> - <c:out value="${b.ubicacion}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="actions">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                    <a class="btn" href="${urlIngresos}">Cancelar</a>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
