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
    <title>Gestión de Compras - Dashboard</title>
    <link rel="stylesheet" href="${css}">
</head>
<body>
<div class="container">

    <div class="topbar">
        <h1>📦 Gestión de Compras</h1>
        <div class="nav">
            <a class="btn btn-primary" href="${urlHome}">Home</a>
            <a class="btn" href="${urlProveedores}">Proveedores</a>
            <a class="btn" href="${urlBodegas}">Bodegas</a>
            <a class="btn" href="${urlIngresos}">Ingresos</a>
        </div>
    </div>

    <div class="hero">
        <div class="hero-inner">
            <h2>Sistema de Gestión de Compras</h2>

            <div class="grid">
                <a class="tile" href="${urlProveedores}">
                    <h3>👥 Proveedores</h3>
                </a>

                <a class="tile" href="${urlBodegas}">
                    <h3>🏢 Bodegas</h3>
                </a>

                <a class="tile" href="${urlIngresos}">
                    <h3>📥 Ingresos</h3>
                </a>
            </div>
        </div>
    </div>

    <div class="footer">
        Sistema de Gestión de Compras • ESPE 2026
    </div>

</div>
</body>
</html>
