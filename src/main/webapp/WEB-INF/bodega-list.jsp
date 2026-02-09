<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url var="css" value="/assets/app.css"/>
<c:url var="urlHome" value="/"/>
<c:url var="urlProveedores" value="/proveedores"/>
<c:url var="urlBodegas" value="/bodegas"/>
<c:url var="urlIngresos" value="/ingresos"/>
<c:url var="urlBodNew" value="/bodegas">
    <c:param name="action" value="new"/>
</c:url>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Bodegas</title>
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
        <div class="page-header" style="padding:20px 24px; border-bottom:1px solid var(--line); margin:0; gap:12px;">
            <h2>🏢 Bodegas</h2>
            <a class="btn btn-primary" href="${urlBodNew}" style="margin-left:auto">➕ Nueva Bodega</a>
        </div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th style="width:90px;">ID</th>
                    <th>Nombre</th>
                    <th>Ubicación</th>
                    <th style="width:170px;">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty bodegas}">
                        <tr><td colspan="4" class="empty">No hay registros.</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="b" items="${bodegas}">
                            <c:url var="urlBodEdit" value="/bodegas">
                                <c:param name="action" value="edit"/>
                                <c:param name="id" value="${b.idBodega}"/>
                            </c:url>
                            <c:url var="urlBodDelete" value="/bodegas">
                                <c:param name="action" value="delete"/>
                                <c:param name="id" value="${b.idBodega}"/>
                            </c:url>

                            <tr>
                                <td><c:out value="${b.idBodega}"/></td>
                                <td><c:out value="${b.nombre}"/></td>
                                <td><c:out value="${b.ubicacion}"/></td>
                                <td class="actions">
                                    <a href="${urlBodEdit}">✏️ Editar</a>
                                    <a class="danger" href="${urlBodDelete}"
                                       onclick="return confirm('¿Estás seguro de que deseas eliminar esta bodega? Esta acción no se puede deshacer.');">🗑️ Eliminar</a>
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
