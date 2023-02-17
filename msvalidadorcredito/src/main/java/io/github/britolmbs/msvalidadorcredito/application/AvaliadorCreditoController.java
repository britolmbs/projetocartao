package io.github.britolmbs.msvalidadorcredito.application;

import io.github.britolmbs.msvalidadorcredito.application.ex.DadosClienteNotFoundException;
import io.github.britolmbs.msvalidadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import io.github.britolmbs.msvalidadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvalidadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "sutucao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return  ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
           return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());       }

    }
}
