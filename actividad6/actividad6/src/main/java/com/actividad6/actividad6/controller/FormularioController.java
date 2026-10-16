package com.actividad6.actividad6.controller;

import com.actividad6.actividad6.Formulario;
import com.actividad6.actividad6.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class FormularioController {

    Map<String, String> usuarios = new HashMap<>();

    @GetMapping("/registro")
    public String mostrarFormulario() {
        return "formulario";
    }

    @PostMapping("/registro")
    @ResponseBody
    public String procesarFormulario(@ModelAttribute Formulario formulario) {

        if (formulario.getNombre() == null) {
            return "El nombre es obligatorio";
        }

        if (formulario.getCorreo() == null) {
            return "El correo es obligatorio";
        }

        if (formulario.getApellidos() == null) {
            formulario.setApellidos("");
        }

        boolean generada = formulario.getContraseña() == null;

        if (generada) {
            Random random = new Random();
            int numero = random.nextInt(1000000);
            String contraseña = String.valueOf(numero);

            while (contraseña.length() < 6) {
                contraseña = "0" + contraseña;
            }

            formulario.setContraseña(contraseña);
        }
        usuarios.put(formulario.getCorreo(), formulario.getContraseña());

        String respuesta = "Usuario registrado: "
                + formulario.getNombre() + " "
                + formulario.getApellidos()
                + ", Correo: " + formulario.getCorreo();

        if (generada) {
            respuesta += ", Contraseña generada: " + formulario.getContraseña();
        }

        return respuesta;
    }
    @GetMapping("/login")
    public String mostrarLogin(){
        return"inicio";
    }
    @PostMapping
    @ResponseBody
    public String procesarLogin (@ModelAttribute Login login){
        String correo= login.getCorreo();
        String contraseña= login.getContraseña();

        String contraseñaMap= usuarios.get(correo);
        if(!usuarios.containsKey(correo)){
            return "Error el usuario no existe";
        }

        if(!contraseña.equals(contraseñaMap)){
            return "La contraseña es incorrecta";
        }
        return "Inicio de sesión completado";
    }
}
