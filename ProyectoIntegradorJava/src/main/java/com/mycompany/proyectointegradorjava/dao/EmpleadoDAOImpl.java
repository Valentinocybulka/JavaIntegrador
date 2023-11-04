package com.mycompany.proyectointegradorjava.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mycompany.proyectointegradorjava.conexion.Conexion;
import com.mycompany.proyectointegradorjava.model.Empleado;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

    @Override
    public boolean guardar(Empleado empleado) {
        String sql = null;
        estadoOperacion = false;
        try {
            connection = obtenerConexion();
            connection.setAutoCommit(false);
            sql = "INSERT INTO productos (codigo, producto, marca, precio, stock, vendidos) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, empleado.getCodigo());
            statement.setString(2, empleado.getProducto());
            statement.setString(3, empleado.getMarca());
            statement.setInt(4, empleado.getPrecio());
            statement.setInt(5, empleado.getStock());
            statement.setInt(6, empleado.getVendidos());

            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return estadoOperacion;
    }

    @Override
    public boolean editar(Empleado empleado) {
        String sql = null;
        estadoOperacion = false;
        try {
            connection = obtenerConexion();
            connection.setAutoCommit(false);
            sql = "UPDATE productos SET producto=?, marca=?, precio=?, stock=?, vendidos=? WHERE codigo=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, empleado.getProducto());
            statement.setString(2, empleado.getMarca());
            statement.setInt(3, empleado.getPrecio());
            statement.setInt(4, empleado.getStock());
            statement.setInt(5, empleado.getVendidos());
            statement.setInt(6, empleado.getCodigo());
            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return estadoOperacion;
    }

    /**
     *
     * @param idEmpleado
     * @return
     */
    @Override
    public boolean eliminar(int idEmpleado) {
        String sql = null;
        estadoOperacion = false;
        try {
            connection = obtenerConexion();
            connection.setAutoCommit(false);
            sql = "DELETE FROM productos WHERE codigo=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idEmpleado);
            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return estadoOperacion;
    }

    @Override
    public List<Empleado> obtenerEmpleados() {
        ResultSet resultSet = null;
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = null;
        estadoOperacion = false;
        try {
            sql = "SELECT * FROM productos";
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Empleado e = new Empleado();
                e.setCodigo(resultSet.getInt(1));
                e.setProducto(resultSet.getString(2));
                e.setMarca(resultSet.getString(3));
                e.setPrecio(resultSet.getInt(4));
                e.setStock(resultSet.getInt(5));
                e.setVendidos(resultSet.getInt(6));
                listaEmpleados.add(e);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    @Override
    public Empleado obtenerEmpleado(int idEmpleado) {
        ResultSet resultSet = null;
        Empleado emp = new Empleado();
        String sql = null;
        estadoOperacion = false;
        try {
            sql = "SELECT * FROM productos WHERE codigo = ?";
            connection = obtenerConexion();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idEmpleado);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                emp.setCodigo(resultSet.getInt(1));
                emp.setProducto(resultSet.getString(2));
                emp.setMarca(resultSet.getString(3));
                emp.setPrecio(resultSet.getInt(4));
                emp.setStock(resultSet.getInt(5));
                emp.setVendidos(resultSet.getInt(6));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    @Override
    public List<Empleado> obtenerProductos() {
        List<Empleado> listaEmpleados = new ArrayList<>();

        return listaEmpleados;
    }

    @Override
    public Empleado obtenerProducto(int idEmpleado) {
        Empleado empleado = null;
        Iterable<Empleado> listaEmpleados = null;

        for (Empleado emp : listaEmpleados) {
            if (emp.getCodigo() == idEmpleado) {
                empleado = emp;
                break;
            }
        }

        return empleado;
    }
}