package io.github.britolmbs.mscartoes.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cartoes")
public class cartoesResource {

    @GetMapping
    public String status(){
        return "ok";
    }
}
