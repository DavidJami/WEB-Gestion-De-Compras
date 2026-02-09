package com.espe.gestioncompras.dao;

import com.espe.gestioncompras.model.IngresoInventario;
import com.espe.gestioncompras.utils.Db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Klever Jami
 */
public class IngresoInventarioDAO {

    public List<IngresoInventario> findAll() {
        String sql = """
            SELECT i.id_ingreso, i.fecha, i.cantidad, i.id_proveedor, i.id_bodega,
                   p.nombre AS proveedor_nombre,
                   b.nombre AS bodega_nombre
            FROM ingreso_inventario i
            JOIN proveedor p ON p.id_proveedor = i.id_proveedor
            JOIN bodega b ON b.id_bodega = i.id_bodega
            ORDER BY i.id_ingreso DESC;
            """;

        List<IngresoInventario> list = new ArrayList<>();

        try (Connection conn = Db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                IngresoInventario i = new IngresoInventario();
                i.setIdIngreso(rs.getLong("id_ingreso"));
                i.setFecha(rs.getDate("fecha"));
                i.setCantidad(rs.getInt("cantidad"));
                i.setIdProveedor(rs.getLong("id_proveedor"));
                i.setIdBodega(rs.getLong("id_bodega"));
                i.setProveedorNombre(rs.getString("proveedor_nombre"));
                i.setBodegaNombre(rs.getString("bodega_nombre"));
                list.add(i);
            }
            return list;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public IngresoInventario findById(long idIngreso) {
        String sql = """
            SELECT id_ingreso, fecha, cantidad, id_proveedor, id_bodega
            FROM ingreso_inventario
            WHERE id_ingreso = ?;
            """;

        try (Connection conn = Db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idIngreso);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return new IngresoInventario(
                        rs.getLong("id_ingreso"),
                        rs.getDate("fecha"),
                        rs.getInt("cantidad"),
                        rs.getLong("id_proveedor"),
                        rs.getLong("id_bodega")
                );
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public long create(IngresoInventario i) {
        String sql = """
            INSERT INTO ingreso_inventario(fecha, cantidad, id_proveedor, id_bodega)
            VALUES(?, ?, ?, ?);
            """;

        try (Connection conn = Db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, i.getFecha());
            ps.setInt(2, i.getCantidad());
            ps.setLong(3, i.getIdProveedor());
            ps.setLong(4, i.getIdBodega());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getLong(1) : 0;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean update(IngresoInventario i) {
        String sql = """
            UPDATE ingreso_inventario
            SET fecha = ?, cantidad = ?, id_proveedor = ?, id_bodega = ?
            WHERE id_ingreso = ?;
            """;

        try (Connection conn = Db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, i.getFecha());
            ps.setInt(2, i.getCantidad());
            ps.setLong(3, i.getIdProveedor());
            ps.setLong(4, i.getIdBodega());
            ps.setLong(5, i.getIdIngreso());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean delete(long idIngreso) {
        String sql = "DELETE FROM ingreso_inventario WHERE id_ingreso = ?;";

        try (Connection conn = Db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idIngreso);
            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
