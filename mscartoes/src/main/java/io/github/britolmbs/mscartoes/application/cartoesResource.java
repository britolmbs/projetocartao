package io.github.britolmbs.mscartoes.application;

import io.github.britolmbs.mscartoes.application.represetation.CartaoSaveRequest;
import io.github.britolmbs.mscartoes.application.represetation.CartoesPorClientesResponse;
import io.github.britolmbs.mscartoes.domain.Cartao;
import io.github.britolmbs.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class cartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status(){
        return "ok";
    }

   @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request){
       Cartao cartao = request.toModel();
       cartaoService.save(cartao);
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }

   @GetMapping(params = "renda")
   public ResponseEntity<List<Cartao>> getCartaoRenadaAteh(@RequestParam("renda") Long renda){
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
   }

   @GetMapping(params = "cpf")
   public ResponseEntity<List<CartoesPorClientesResponse>> getCartoesByCpf(@RequestParam("cpf") String cpf){
        List<ClienteCartao> lista = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClientesResponse> resultList = lista.stream().map(CartoesPorClientesResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
   }
}
