package com.actividad4.actividad4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablaController {

    @GetMapping(value = "/tabla", produces = "text/html")
    public String generarTabla(
            @RequestParam(required = false) String filas,
            @RequestParam(required = false) String columnas) {

        int numFilas = convertir(filas);
        int numColumnas = convertir(columnas);

        numFilas = Math.max(1, Math.min(20, numFilas));
        numColumnas = Math.max(1, Math.min(20, numColumnas));

        String html = "<html><head><title>Tabla</title></head><body>";
        html += "<table border='1'><tr><th></th>";

        for (int j = 1; j <= numColumnas; j++) {
            html += "<th>Columna " + j + "</th>";
        }

        html += "</tr>";

        for (int i = 1; i <= numFilas; i++) {
            html += "<tr><th>Fila " + i + "</th>";

            for (int j = 1; j <= numColumnas; j++) {
                html += "<td>Fila " + i + ", Columna " + j + "</td>";
            }

            html += "</tr>";
        }

        html += "</table></body></html>";

        return html;
    }

    private int convertir(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}


