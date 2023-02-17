package io.github.britolmbs.msvalidadorcredito.application;

import feign.FeignException;
import io.github.britolmbs.msvalidadorcredito.application.ex.DadosClienteNotFoundException;
import io.github.britolmbs.msvalidadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import io.github.britolmbs.msvalidadorcredito.domain.model.CartaoCliente;
import io.github.britolmbs.msvalidadorcredito.domain.model.DadosCliente;
import io.github.britolmbs.msvalidadorcredito.domain.model.SituacaoCliente;
import io.github.britolmbs.msvalidadorcredito.infra.clients.CartoesResourcesClient;
import io.github.britolmbs.msvalidadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvalidadorCreditoService {

    private final ClienteResourceClient clienteClient;
    private final CartoesResourcesClient cartoesClient;
    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }
}
