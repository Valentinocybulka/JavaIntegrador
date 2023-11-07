package com.mycompany.proyectointegradorjava.controller;

import com.mycompany.proyectointegradorjava.dao.EmpleadoDAO;
import com.mycompany.proyectointegradorjava.dao.EmpleadoDAOImpl;
import com.mycompany.proyectointegradorjava.model.Empleado;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmpleadoController", urlPatterns = {"/EmpleadoController"})
public class EmpleadoController extends HttpServlet {

    private EmpleadoDAO empleadoDAO;

    @Override
    public void init() throws ServletException {
        empleadoDAO = new EmpleadoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");

        switch (opcion) {
            case "crear": {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            case "listar": {
                List<Empleado> lista = empleadoDAO.obtenerEmpleados();
                request.setAttribute("lista", lista);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            case "editar": {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                Empleado emp = empleadoDAO.obtenerEmpleado(codigo);
                request.setAttribute("empleado", emp);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            case "eliminar": {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                empleadoDAO.eliminar(codigo);
                System.out.println("Registro eliminado satisfactoriamente...");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        if (opcion.equals("guardar")) {
            Empleado empleado = new Empleado();
            empleado.setProducto(request.getParameter("producto"));
            empleado.setMarca(request.getParameter("marca"));
            empleado.setPrecio(Integer.parseInt(request.getParameter("precio")));
            empleado.setStock(Integer.parseInt(request.getParameter("stock")));
            empleado.setVendidos(Integer.parseInt(request.getParameter("vendidos")));
            empleadoDAO.guardar(empleado);
            System.out.println("Registro guardado satisfactoriamente...");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else if (opcion.equals("editar")) {
            Empleado empleado = new Empleado();
            empleado.setCodigo(Integer.parseInt(request.getParameter("codigo")));
            empleado.setProducto(request.getParameter("producto"));
            empleado.setMarca(request.getParameter("marca"));
            empleado.setPrecio(Integer.parseInt(request.getParameter("precio")));
            empleado.setStock(Integer.parseInt(request.getParameter("stock")));
            empleado.setVendidos(Integer.parseInt(request.getParameter("vendidos")));
            empleadoDAO.editar(empleado);
            System.out.println("Registro editado satisfactoriamente...");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
