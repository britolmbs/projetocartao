package io.github.britolmbs.msvalidadorcredito.application.ex;

public class DadosClienteNotFoundException extends Exception {
    public DadosClienteNotFoundException() {
        super("Dados do Cliente não encontrados para o CPF Informado.");
    }
}
