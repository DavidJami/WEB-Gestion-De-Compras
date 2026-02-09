package com.espe.gestioncompras.model;

import java.sql.Date;

/**
 *
 * @author Klever Jami
 */
public class IngresoInventario {

    private Long idIngreso;
    private Date fecha;
    private int cantidad;
    private long idProveedor;
    private long idBodega;

    private String proveedorNombre;
    private String bodegaNombre;

    public IngresoInventario() {
    }

    public IngresoInventario(Long idIngreso, Date fecha, int cantidad, long idProveedor, long idBodega) {
        this.idIngreso = idIngreso;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.idProveedor = idProveedor;
        this.idBodega = idBodega;
    }

    public long getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(long idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(long idBodega) {
        this.idBodega = idBodega;
    }

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }

    public String getBodegaNombre() {
        return bodegaNombre;
    }

    public void setBodegaNombre(String bodegaNombre) {
        this.bodegaNombre = bodegaNombre;
    }
}
