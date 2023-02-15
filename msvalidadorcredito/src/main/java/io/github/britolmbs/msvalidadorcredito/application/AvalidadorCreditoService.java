package io.github.britolmbs.msvalidadorcredito.application;

import io.github.britolmbs.msvalidadorcredito.domain.model.DadosCliente;
import io.github.britolmbs.msvalidadorcredito.domain.model.SituacaoCliente;
import io.github.britolmbs.msvalidadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvalidadorCreditoService {

    private final ClienteResourceClient clienteClient;

    public SituacaoCliente obterSituacaoCliente(String cpf){

        ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .build();
    }
}
