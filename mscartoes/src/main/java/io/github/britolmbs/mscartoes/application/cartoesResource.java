package io.github.britolmbs.mscartoes.application;

import io.github.britolmbs.mscartoes.application.represetation.CartaoSaveRequest;
import io.github.britolmbs.mscartoes.domain.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class cartoesResource {

    private final CartaoService service;

    @GetMapping
    public String status(){
        return "ok";
    }

   @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request){
       Cartao cartao = request.toModel();
       service.save(cartao);
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }

   @GetMapping(params = "renda")
   public ResponseEntity<List<Cartao>> getCartaoRenadaAteh(@RequestParam("renda") Long renda){
        List<Cartao> list = service.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
   }
}
