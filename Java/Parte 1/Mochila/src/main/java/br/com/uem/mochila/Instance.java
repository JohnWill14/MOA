package br.com.uem.mochila;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Instance {
    private int[] pesos;
    private int[] valores;
    private int capacidade;
    private int qtd;
}
