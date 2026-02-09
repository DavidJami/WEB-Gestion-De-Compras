<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url var="css" value="/assets/app.css"/>
<c:url var="urlHome" value="/"/>
<c:url var="urlProveedores" value="/proveedores"/>
<c:url var="urlBodegas" value="/bodegas"/>
<c:url var="urlIngresos" value="/ingresos"/>
<c:url var="urlIngNew" value="/ingresos">
    <c:param name="action" value="new"/>
</c:url>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Ingresos</title>
    <link rel="stylesheet" href="${css}">
</head>
<body>
<div class="container">

    <div class="topbar">
        <h1>� Sistema de Gestión</h1>
        <div class="nav">
            <a class="btn" href="${urlHome}">Home</a>
            <a class="btn" href="${urlProveedores}">Proveedores</a>
            <a class="btn" href="${urlBodegas}">Bodegas</a>
            <a class="btn" href="${urlIngresos}">Ingresos</a>
        </div>
    </div>

    <div class="card">
        <div class="page-header" style="padding:20px 24px; border-bottom:1px solid var(--line); margin:0; gap:12px;">
            <h2>📥 Ingresos de Inventario</h2>
            <a class="btn btn-primary" href="${urlIngNew}" style="margin-left:auto">➕ Nuevo Ingreso</a>
        </div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th style="width:90px;">ID</th>
                    <th style="width:140px;">Fecha</th>
                    <th class="num" style="width:140px;">Cantidad</th>
                    <th>Proveedor</th>
                    <th>Bodega</th>
                    <th style="width:170px;">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty ingresos}">
                        <tr><td colspan="6" class="empty">No hay registros.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="i" items="${ingresos}">
                            <c:url var="urlIngEdit" value="/ingresos">
                                <c:param name="action" value="edit"/>
                                <c:param name="id" value="${i.idIngreso}"/>
                            </c:url>
                            <c:url var="urlIngDelete" value="/ingresos">
                                <c:param name="action" value="delete"/>
                                <c:param name="id" value="${i.idIngreso}"/>
                            </c:url>

                            <tr>
                                <td><c:out value="${i.idIngreso}"/></td>
                                <td><c:out value="${i.fecha}"/></td>
                                <td class="num"><c:out value="${i.cantidad}"/></td>
                                <td><c:out value="${i.proveedorNombre}"/></td>
                                <td><c:out value="${i.bodegaNombre}"/></td>
                                <td class="actions">
                                    <a href="${urlIngEdit}">✏️ Editar</a>
                                    <a class="danger" href="${urlIngDelete}"
                                       onclick="return confirm('¿Estás seguro de que deseas eliminar este ingreso? Esta acción no se puede deshacer.');">🗑️ Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>

</div>
</body>
</html>
