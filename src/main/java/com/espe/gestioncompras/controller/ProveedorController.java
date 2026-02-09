package com.espe.gestioncompras.controller;

import com.espe.gestioncompras.dao.ProveedorDAO;
import com.espe.gestioncompras.model.Proveedor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Klever Jami
 */
@WebServlet("/proveedores")
public class ProveedorController extends HttpServlet {

    private final ProveedorDAO dao = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null || action.isBlank()) action = "list";

        switch (action) {
            case "new" -> showForm(req, resp, new Proveedor(), "create");
            case "edit" -> showEdit(req, resp);
            case "delete" -> doDeleteById(req, resp);
            default -> doList(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create" -> doCreate(req, resp);
            case "update" -> doUpdate(req, resp);
            default -> resp.sendRedirect(req.getContextPath() + "/proveedores");
        }
    }

    private void doList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Proveedor> proveedores = dao.findAll();
        req.setAttribute("proveedores", proveedores);
        req.getRequestDispatcher("/WEB-INF/proveedor-list.jsp").forward(req, resp); // forward [web:170]
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        long id = Long.parseLong(req.getParameter("id"));
        Proveedor p = dao.findById(id);

        if (p == null) {
            resp.sendRedirect(req.getContextPath() + "/proveedores");
            return;
        }
        showForm(req, resp, p, "update");
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, Proveedor p, String formAction)
            throws ServletException, IOException {

        req.setAttribute("proveedor", p);
        req.setAttribute("formAction", formAction);
        req.getRequestDispatcher("/WEB-INF/proveedor-form.jsp").forward(req, resp); // forward [web:170]
    }

    private void doCreate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Proveedor p = parseProveedor(req, false);
        dao.create(p);
        resp.sendRedirect(req.getContextPath() + "/proveedores");
    }

    private void doUpdate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Proveedor p = parseProveedor(req, true);
        dao.update(p);
        resp.sendRedirect(req.getContextPath() + "/proveedores");
    }

    private void doDeleteById(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        long id = Long.parseLong(req.getParameter("id"));
        dao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/proveedores");
    }

    private Proveedor parseProveedor(HttpServletRequest req, boolean withId)
            throws ServletException {

        try {
            String nombre = req.getParameter("nombre");
            String ruc = req.getParameter("ruc");

            if (nombre == null || nombre.isBlank()) throw new ServletException("Nombre requerido");
            if (ruc == null || ruc.isBlank()) throw new ServletException("RUC requerido");
            if (ruc.trim().length() != 13) throw new ServletException("RUC debe tener 13 dígitos");

            Proveedor p = new Proveedor(null, nombre.trim(), ruc.trim());
            if (withId) p.setIdProveedor(Long.parseLong(req.getParameter("idProveedor")));
            return p;
        } catch (NumberFormatException ex) {
            throw new ServletException("Datos inválidos", ex);
        }
    }
}
