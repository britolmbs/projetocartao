package io.github.britolmbs.msclientes.application.representation;

import io.github.britolmbs.msclientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteSalveRequest {
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
