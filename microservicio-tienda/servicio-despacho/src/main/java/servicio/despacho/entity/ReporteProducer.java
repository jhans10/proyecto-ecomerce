package servicio.despacho.entity;

import application.entity.models.EntityServiceProducto.Producto;

import java.math.BigDecimal;
import java.util.Date;


public class Reporte {

    private Long id;

    private String productoNombre;

    private String productoMarca;

    private BigDecimal productoPrecio;

    private Integer productoCantidad;

    private Date compraFecha;

    private Date compraHora;

    private String categoriaNombre;

    private Long usuarioId;

    public  Reporte(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getProductoMarca() {
        return productoMarca;
    }

    public void setProductoMarca(String productoMarca) {
        this.productoMarca = productoMarca;
    }

    public BigDecimal getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(BigDecimal productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

    public Integer getProductoCantidad() {
        return productoCantidad;
    }

    public void setProductoCantidad(Integer productoCantidad) {
        this.productoCantidad = productoCantidad;
    }

    public Date getCompraFecha() {
        return compraFecha;
    }

    public void setCompraFecha(Date compraFecha) {
        this.compraFecha = compraFecha;
    }

    public Date getCompraHora() {
        return compraHora;
    }

    public void setCompraHora(Date compraHora) {
        this.compraHora = compraHora;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
