package com.isft194.entregaComida.mapper;


import com.isft194.entregaComida.dto.request.ItemRequest;
import com.isft194.entregaComida.dto.response.ItemResponse;
import com.isft194.entregaComida.model.Item;
import com.isft194.entregaComida.model.Producto;
import com.isft194.entregaComida.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMapper {

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private ProductoService productoService;

    public Item itemRequestToItem(ItemRequest itemRequest) {
        Producto producto = productoService.findProductoById(itemRequest.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return Item.builder()
                .producto(producto)
                .cantidad(itemRequest.getCantidad())
                .precio(itemRequest.getCantidad() * producto.getPrecio())
                .build();
    }

    public ItemResponse itemToItemResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .producto(productoMapper.productoToProductoResponse(item.getProducto()))
                .cantidad(item.getCantidad())
                .precio(item.getPrecio())
                .build();
    }
}