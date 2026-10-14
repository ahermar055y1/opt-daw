package com.actividad5.actividad5.controllers;

import com.actividad5.actividad5.Cliente;
import com.actividad5.actividad5.Pedido;
import com.actividad5.actividad5.Producto;
import com.actividad5.actividad5.Respuesta;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {
    @PostMapping ("/pedido")
    public Respuesta PedidoController(@RequestBody Pedido pedido){
        int cantidad= 0;
        double total=0;
        for (Producto producto : pedido.getProductos()){
            cantidad+= producto.getCantidad();
            total+= producto.getCantidad() * producto.getPrecioUnitario();
        }

        Respuesta respuesta =new Respuesta(
                pedido.getCliente().getNombre(),
                cantidad, total, "Pedido recibido correctamente");
        return respuesta;
    }

}
