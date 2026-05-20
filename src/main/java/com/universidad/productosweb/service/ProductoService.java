package com.universidad.productosweb.service;

import com.universidad.productosweb.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoService {

    private final Map<Long, Producto> almacen = new HashMap<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public ProductoService() {
        guardar(new Producto(null, "Monitor", "Monitor 24 pulgadas", 690.00));
        guardar(new Producto(null, "Teclado", "Teclado mecánico", 199.90));
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(almacen.values());
    }

    public Optional<Producto> buscarPorId(Long id) {
        return Optional.ofNullable(almacen.get(id));
    }

    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(secuencia.getAndIncrement());
        }
        almacen.put(producto.getId(), producto);
        return producto;
    }

    public void eliminar(Long id) {
        almacen.remove(id);
    }
}
