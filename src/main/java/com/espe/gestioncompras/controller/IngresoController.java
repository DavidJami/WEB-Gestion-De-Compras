package com.espe.gestioncompras.controller;

import com.espe.gestioncompras.dao.BodegaDAO;
import com.espe.gestioncompras.dao.IngresoInventarioDAO;
import com.espe.gestioncompras.dao.ProveedorDAO;
import com.espe.gestioncompras.model.IngresoInventario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Klever Jami
 */
@WebServlet("/ingresos")
public class IngresoController extends HttpServlet {

    private final IngresoInventarioDAO ingresoDAO = new IngresoInventarioDAO();
    private final ProveedorDAO proveedorDAO = new ProveedorDAO();
    private final BodegaDAO bodegaDAO = new BodegaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null || action.isBlank()) {
            action = "list";
        }

        switch (action) {
            case "new" ->
                showNew(req, resp);
            case "edit" ->
                showEdit(req, resp);
            case "delete" ->
                doDeleteById(req, resp);
            default ->
                doList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create" ->
                doCreate(req, resp);
            case "update" ->
                doUpdate(req, resp);
            default ->
                resp.sendRedirect(req.getContextPath() + "/ingresos");
        }
    }

    private void doList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<IngresoInventario> ingresos = ingresoDAO.findAll();
        req.setAttribute("ingresos", ingresos);
        req.getRequestDispatcher("/WEB-INF/ingreso-list.jsp").forward(req, resp);
    }

    private void showNew(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        loadCombos(req);
        req.setAttribute("ingreso", new IngresoInventario());
        req.setAttribute("formAction", "create");
        req.getRequestDispatcher("/WEB-INF/ingreso-form.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        long id = Long.parseLong(req.getParameter("id"));
        IngresoInventario ingreso = ingresoDAO.findById(id);

        if (ingreso == null) {
            resp.sendRedirect(req.getContextPath() + "/ingresos");
            return;
        }

        loadCombos(req);
        req.setAttribute("ingreso", ingreso);
        req.setAttribute("formAction", "update");
        req.getRequestDispatcher("/WEB-INF/ingreso-form.jsp").forward(req, resp);
    }

    private void loadCombos(HttpServletRequest req) {
        req.setAttribute("proveedores", proveedorDAO.findAll());
        req.setAttribute("bodegas", bodegaDAO.findAll());
    }

    private void doCreate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        IngresoInventario i = parseIngreso(req, false);
        ingresoDAO.create(i);
        resp.sendRedirect(req.getContextPath() + "/ingresos");
    }

    private void doUpdate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        IngresoInventario i = parseIngreso(req, true);
        ingresoDAO.update(i);
        resp.sendRedirect(req.getContextPath() + "/ingresos");
    }

    private void doDeleteById(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        long id = Long.parseLong(req.getParameter("id"));
        ingresoDAO.delete(id);
        resp.sendRedirect(req.getContextPath() + "/ingresos");
    }

    private IngresoInventario parseIngreso(HttpServletRequest req, boolean withId)
            throws ServletException {

        try {
            String fechaStr = req.getParameter("fecha");
            String cantidadStr = req.getParameter("cantidad");
            String idProveedorStr = req.getParameter("idProveedor");
            String idBodegaStr = req.getParameter("idBodega");

            if (fechaStr == null || fechaStr.isBlank()) {
                throw new ServletException("Fecha requerida");
            }

            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                throw new ServletException("Cantidad debe ser mayor a 0");
            }

            long idProveedor = Long.parseLong(idProveedorStr);
            long idBodega = Long.parseLong(idBodegaStr);

            IngresoInventario i = new IngresoInventario(null, Date.valueOf(fechaStr), cantidad, idProveedor, idBodega);
            if (withId) {
                i.setIdIngreso(Long.parseLong(req.getParameter("idIngreso")));
            }
            return i;

        } catch (NumberFormatException ex) {
            throw new ServletException("Cantidad/IDs inválidos", ex);
        } catch (IllegalArgumentException ex) {
            throw new ServletException("Fecha inválida", ex);
        }

    }
}
