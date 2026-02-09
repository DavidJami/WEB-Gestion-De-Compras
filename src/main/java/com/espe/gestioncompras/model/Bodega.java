package com.espe.gestioncompras.model;

/**
 *
 * @author Klever Jami
 */
public class Bodega {
    private Long idBodega;
    private String nombre;
    private String ubicacion;

    public Bodega() {
    }

    public Bodega(Long idBodega, String nombre, String ubicacion) {
        this.idBodega = idBodega;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public Long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Long idBodega) {
        this.idBodega = idBodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    
}
