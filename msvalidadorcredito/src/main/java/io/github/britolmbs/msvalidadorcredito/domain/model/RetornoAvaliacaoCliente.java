package io.github.britolmbs.msvalidadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
private List<CartaoAprovado> cartoes;
}
