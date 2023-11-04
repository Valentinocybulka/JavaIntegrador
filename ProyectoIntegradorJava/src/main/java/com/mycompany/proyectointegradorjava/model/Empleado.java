package com.mycompany.proyectointegradorjava.model;

public class Empleado {
private int codigo;
private String producto;
private String marca;
private int precio;
private int stock;
private int vendidos;

   public Empleado() {
    }
   
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getVendidos() {
        return vendidos;
    }

    public void setVendidos(int vendidos) {
        this.vendidos = vendidos;
    }

    @Override
    public String toString() {
        return "productos{" + "codigo=" + codigo + ", productos=" + producto + ", marca=" + marca + ", precio=" + precio + ", stock=" + stock + ", vendidos=" + vendidos + '}';
    }
}