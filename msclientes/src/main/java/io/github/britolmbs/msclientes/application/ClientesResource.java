package io.github.britolmbs.msclientes.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClientesResource {

    public String status(){
        return "ok";
    }
}
