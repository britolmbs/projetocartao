package io.github.britolmbs.msvalidadorcredito.application;

import io.github.britolmbs.msvalidadorcredito.domain.model.CartaoCliente;
import io.github.britolmbs.msvalidadorcredito.domain.model.DadosCliente;
import io.github.britolmbs.msvalidadorcredito.domain.model.SituacaoCliente;
import io.github.britolmbs.msvalidadorcredito.infra.clients.CartoesResourcesClient;
import io.github.britolmbs.msvalidadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvalidadorCreditoService {

    private final ClienteResourceClient clienteClient;
    private final CartoesResourcesClient cartoesClient;
    public SituacaoCliente obterSituacaoCliente(String cpf){

        ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
        ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);
        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .cartoes(cartoesResponse.getBody())
                .build();
    }

}
