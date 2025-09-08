package com.juliana.ecommerce.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.juliana.ecommerce.model.Carrito;
import com.juliana.ecommerce.model.ItemCarrito;
import com.juliana.ecommerce.model.Producto;
import com.juliana.ecommerce.model.Usuario;
import com.juliana.ecommerce.repository.CarritoRepository;
import com.juliana.ecommerce.repository.ItemCarritoRepository;
import com.juliana.ecommerce.repository.ProductoRepository;
import com.juliana.ecommerce.service.CarritoService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepo;
    private final ProductoRepository productoRepo;
    private final ItemCarritoRepository itemRepo;

    public CarritoServiceImpl(CarritoRepository carritoRepo,
                              ProductoRepository productoRepo,
                              ItemCarritoRepository itemRepo) {
        this.carritoRepo = carritoRepo;
        this.productoRepo = productoRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public Carrito obtenerOCrear(Usuario usuario) {
        return carritoRepo.findByUsuario(usuario)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuario(usuario);
                    return carritoRepo.save(nuevo);
                });
    }

    @Override
    public Carrito agregarProducto(Usuario usuario, Long productoId, int cantidad) {
        Carrito carrito = obtenerOCrear(usuario);
        Producto producto = productoRepo.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // buscar si ya está el producto en el carrito
        ItemCarrito item = carrito.getItems().stream()
                .filter(i -> i.getProducto().getId().equals(productoId))
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setCantidad(item.getCantidad() + cantidad);
        } else {
            item = new ItemCarrito();
            item.setProducto(producto);
            item.setCantidad(cantidad);
            item.setPrecioUnitario(producto.getPrecio());
            item.setCarrito(carrito);
            carrito.getItems().add(item);
        }

        return carritoRepo.save(carrito);
    }

    @Override
    public Carrito actualizarCantidad(Usuario usuario, Long itemId, int cantidad) {
        Carrito carrito = obtenerOCrear(usuario);
        ItemCarrito item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        if (!item.getCarrito().getId().equals(carrito.getId())) {
            throw new RuntimeException("El item no pertenece a este carrito");
        }

        item.setCantidad(cantidad);
        itemRepo.save(item);
        return carrito;
    }

    @Override
    public Carrito eliminarItem(Usuario usuario, Long itemId) {
        Carrito carrito = obtenerOCrear(usuario);
        ItemCarrito item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        if (!item.getCarrito().getId().equals(carrito.getId())) {
            throw new RuntimeException("El item no pertenece a este carrito");
        }

        carrito.getItems().remove(item);
        itemRepo.delete(item);
        return carritoRepo.save(carrito);
    }

    @Override
    public void limpiar(Usuario usuario) {
        Carrito carrito = obtenerOCrear(usuario);
        carrito.getItems().clear();
        carritoRepo.save(carrito);
    }
}