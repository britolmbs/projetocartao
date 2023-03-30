package io.github.britolmbs.msvalidadorcredito.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class RetornoAvaliacaoCliente {
private List<CartaoAprovado> cartoes;
}
