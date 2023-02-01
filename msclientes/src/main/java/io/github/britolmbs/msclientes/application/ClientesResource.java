package io.github.britolmbs.msclientes.application;

import io.github.britolmbs.msclientes.application.representation.ClienteSalveRequest;
import io.github.britolmbs.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClientesResource {

    private final ClienteService service;

    public String status(){
        return "ok";
    }
    @PostMapping
    public ResponseEntity salve(@RequestBody ClienteSalveRequest request){
       Cliente cliente = request.toModel();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();

    }
    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        Optional<Cliente> cliente = service.getByCpf(cpf);
        if(cliente.isEmpty()){
        return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(cliente);
    }




}

