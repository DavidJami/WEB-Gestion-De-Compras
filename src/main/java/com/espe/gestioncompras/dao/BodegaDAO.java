package com.espe.gestioncompras.dao;

import com.espe.gestioncompras.model.Bodega;
import com.espe.gestioncompras.utils.Db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klever Jami
 */
public class BodegaDAO {

    public List<Bodega> findAll() {
        String sql = "SELECT id_bodega, nombre, ubicacion FROM bodega ORDER BY id_bodega DESC;";
        List<Bodega> list = new ArrayList<>();

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bodega b = new Bodega();
                b.setIdBodega(rs.getLong("id_bodega"));
                b.setNombre(rs.getString("nombre"));
                b.setUbicacion(rs.getString("ubicacion"));
                list.add(b);
            }
            return list;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Bodega findById(long id) {
        String sql = "SELECT id_bodega, nombre, ubicacion FROM bodega WHERE id_bodega = ?;";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new Bodega(
                        rs.getLong("id_bodega"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion")
                );
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public long create(Bodega b) {
        String sql = "INSERT INTO bodega(nombre, ubicacion) VALUES(?, ?);";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, b.getNombre());
            ps.setString(2, b.getUbicacion());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getLong(1) : 0;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean update(Bodega b) {
        String sql = "UPDATE bodega SET nombre = ?, ubicacion = ? WHERE id_bodega = ?;";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getNombre());
            ps.setString(2, b.getUbicacion());
            ps.setLong(3, b.getIdBodega());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM bodega WHERE id_bodega = ?;";

        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
