package com.espe.gestioncompras.model;

/**
 *
 * @author Klever Jami
 */
public class Proveedor {
    private Long idProveedor;
    private String nombre;
    private String ruc;

    public Proveedor() {
    }
        
    public Proveedor(Long idProveedor, String nombre, String ruc) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.ruc = ruc;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    
}
