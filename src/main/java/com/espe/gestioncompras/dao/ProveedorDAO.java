package com.espe.gestioncompras.dao;

import com.espe.gestioncompras.model.Proveedor;
import com.espe.gestioncompras.utils.Db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klever Jami
 */
public class ProveedorDAO {

    public List<Proveedor> findAll() {
        String sql = "SELECT id_proveedor, nombre, ruc FROM proveedor ORDER BY id_proveedor DESC;";
        List<Proveedor> list = new ArrayList<>();

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getLong("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setRuc(rs.getString("ruc"));
                list.add(p);
            }
            return list;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Proveedor findById(long id) {
        String sql = "SELECT id_proveedor, nombre, ruc FROM proveedor WHERE id_proveedor = ?;";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new Proveedor(
                        rs.getLong("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("ruc")
                );
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public long create(Proveedor p) {
        String sql = "INSERT INTO proveedor(nombre, ruc) VALUES(?, ?);";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getRuc());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getLong(1) : 0;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean update(Proveedor p) {
        String sql = "UPDATE proveedor SET nombre = ?, ruc = ? WHERE id_proveedor = ?;";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getRuc());
            ps.setLong(3, p.getIdProveedor());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?;";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
