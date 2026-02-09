package com.espe.gestioncompras.controller;

import com.espe.gestioncompras.dao.BodegaDAO;
import com.espe.gestioncompras.model.Bodega;
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
@WebServlet("/bodegas")
public class BodegaController extends HttpServlet {

    private final BodegaDAO dao = new BodegaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null || action.isBlank()) action = "list";

        switch (action) {
            case "new" -> showForm(req, resp, new Bodega(), "create");
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
            default -> resp.sendRedirect(req.getContextPath() + "/bodegas");
        }
    }

    private void doList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Bodega> bodegas = dao.findAll();
        req.setAttribute("bodegas", bodegas);
        req.getRequestDispatcher("/WEB-INF/bodega-list.jsp").forward(req, resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        long id = Long.parseLong(req.getParameter("id"));
        Bodega b = dao.findById(id);

        if (b == null) {
            resp.sendRedirect(req.getContextPath() + "/bodegas");
            return;
        }
        showForm(req, resp, b, "update");
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, Bodega b, String formAction)
            throws ServletException, IOException {

        req.setAttribute("bodega", b);
        req.setAttribute("formAction", formAction);
        req.getRequestDispatcher("/WEB-INF/bodega-form.jsp").forward(req, resp);
    }

    private void doCreate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Bodega b = parseBodega(req, false);
        dao.create(b);
        resp.sendRedirect(req.getContextPath() + "/bodegas");
    }

    private void doUpdate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Bodega b = parseBodega(req, true);
        dao.update(b);
        resp.sendRedirect(req.getContextPath() + "/bodegas");
    }

    private void doDeleteById(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        long id = Long.parseLong(req.getParameter("id"));
        dao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/bodegas");
    }

    private Bodega parseBodega(HttpServletRequest req, boolean withId)
            throws ServletException {

        try {
            String nombre = req.getParameter("nombre");
            String ubicacion = req.getParameter("ubicacion");

            if (nombre == null || nombre.isBlank()) throw new ServletException("Nombre requerido");
            if (ubicacion == null || ubicacion.isBlank()) throw new ServletException("Ubicación requerida");

            Bodega b = new Bodega(null, nombre.trim(), ubicacion.trim());
            if (withId) b.setIdBodega(Long.parseLong(req.getParameter("idBodega")));
            return b;

        } catch (NumberFormatException ex) {
            throw new ServletException("Datos inválidos", ex);
        }
    }
}
