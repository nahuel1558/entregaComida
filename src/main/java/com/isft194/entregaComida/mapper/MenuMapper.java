package com.isft194.entregaComida.mapper;


import com.isft194.entregaComida.dto.request.MenuRequest;
import com.isft194.entregaComida.dto.response.MenuResponse;
import com.isft194.entregaComida.dto.response.ProductoResponse;
import com.isft194.entregaComida.dto.response.RestauranteResponse;
import com.isft194.entregaComida.model.Menu;
import com.isft194.entregaComida.model.Producto;
import com.isft194.entregaComida.model.Restaurante;
import com.isft194.entregaComida.service.ProductoService;
import com.isft194.entregaComida.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuMapper {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private RestauranteService restauranteService;

    public Menu menuRequestToMenu(MenuRequest menuRequest) {
        Restaurante restaurante = restauranteService.findRestauranteById(menuRequest.getRestauranteId())
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        List<Producto> productos = menuRequest.getProductosIds().stream()
                .map(productoId -> productoService.findProductoById(productoId)
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado")))
                .collect(Collectors.toList());

        return Menu.builder()
                .nombre(menuRequest.getNombre())
                .restaurante(restaurante)
                .productos(productos)
                .build();
    }

    public MenuResponse menuToMenuResponse(Menu menu) {
        RestauranteResponse restauranteResponse = RestauranteResponse.builder()
                .id(menu.getRestaurante().getId())
                .nombre(menu.getRestaurante().getNombre())
                .build();

        List<ProductoResponse> productosResponses = menu.getProductos().stream()
                .map(producto -> ProductoResponse.builder()
                        .id(producto.getId())
                        .nombre(producto.getNombre())
                        .descripcion(producto.getDescripcion())
                        .precio(producto.getPrecio())
                        .build())
                .collect(Collectors.toList());

        return MenuResponse.builder()
                .id(menu.getId())
                .nombre(menu.getNombre())
                .restaurante(restauranteResponse)
                .productos(productosResponses)
                .build();
    }

}