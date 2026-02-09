<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url var="css" value="/assets/app.css"/>
<c:url var="urlHome" value="/"/>
<c:url var="urlProveedores" value="/proveedores"/>
<c:url var="urlBodegas" value="/bodegas"/>
<c:url var="urlIngresos" value="/ingresos"/>
<c:url var="urlProvNew" value="/proveedores">
    <c:param name="action" value="new"/>
</c:url>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Proveedores</title>
    <link rel="stylesheet" href="${css}">
</head>
<body>
<div class="container">

    <div class="topbar">
        <h1>👥 Sistema de Gestión</h1>
        <div class="nav">
            <a class="btn" href="${urlHome}">Home</a>
            <a class="btn" href="${urlProveedores}">Proveedores</a>
            <a class="btn" href="${urlBodegas}">Bodegas</a>
            <a class="btn" href="${urlIngresos}">Ingresos</a>
        </div>
    </div>

    <div class="card">
        <div class="page-header" style="padding:20px 24px; border-bottom:1px solid var(--line); margin:0; gap:12px;">
            <h2>👥 Proveedores</h2>
            <a class="btn btn-primary" href="${urlProvNew}" style="margin-left:auto">➕ Nuevo Proveedor</a>
        </div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th style="width:90px;">ID</th>
                    <th>Nombre</th>
                    <th style="width:220px;">RUC</th>
                    <th style="width:170px;">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty proveedores}">
                        <tr><td colspan="4" class="empty">No hay registros.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="p" items="${proveedores}">
                            <c:url var="urlProvEdit" value="/proveedores">
                                <c:param name="action" value="edit"/>
                                <c:param name="id" value="${p.idProveedor}"/>
                            </c:url>
                            <c:url var="urlProvDelete" value="/proveedores">
                                <c:param name="action" value="delete"/>
                                <c:param name="id" value="${p.idProveedor}"/>
                            </c:url>

                            <tr>
                                <td><c:out value="${p.idProveedor}"/></td>
                                <td><c:out value="${p.nombre}"/></td>
                                <td><c:out value="${p.ruc}"/></td>
                                <td class="actions">
                                    <a href="${urlProvEdit}">✏️ Editar</a>
                                    <a class="danger" href="${urlProvDelete}"
                                       onclick="return confirm('¿Estás seguro de que deseas eliminar este proveedor? Esta acción no se puede deshacer.');">🗑️ Eliminar</a>
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
