package com.actividad5.actividad5;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {
    @PostMapping("/pedido")
    public Respuesta recibirPedido(@RequestBody Pedido pedido) {

    }
}
