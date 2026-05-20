package com.universidad.productosweb.service;

import com.universidad.productosweb.model.Producto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceTest {

    private final ProductoService service = new ProductoService();

    @Test
    void testObtenerTodos() {
        List<Producto> productos = service.obtenerTodos();
        assertEquals(2, productos.size());
    }

    @Test
    void testGuardar() {
        Producto p = new Producto(null, "Mouse", "Mouse inalámbrico", 49.99);
        Producto guardado = service.guardar(p);
        assertNotNull(guardado.getId());
        assertEquals("Mouse", guardado.getNombre());
        assertEquals(49.99, guardado.getPrecio());
    }

    @Test
    void testBuscarPorId() {
        var encontrado = service.buscarPorId(1L);
        assertTrue(encontrado.isPresent());
        assertEquals("Monitor", encontrado.get().getNombre());
    }

    @Test
    void testBuscarPorIdInexistente() {
        var encontrado = service.buscarPorId(999L);
        assertTrue(encontrado.isEmpty());
    }

    @Test
    void testEliminar() {
        service.eliminar(1L);
        assertTrue(service.buscarPorId(1L).isEmpty());
        assertEquals(1, service.obtenerTodos().size());
    }
}
