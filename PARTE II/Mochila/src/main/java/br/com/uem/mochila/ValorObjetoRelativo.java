package br.com.uem.mochila;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValorObjetoRelativo implements Comparable<ValorObjetoRelativo>{
    private int peso;
    private int valor;
    private double valorRelativo;

    public ValorObjetoRelativo(int peso, int valor) {
        this.peso = peso;
        this.valor = valor;
        this.valorRelativo = valor/(peso*1d);
    }

    @Override
    public int compareTo(ValorObjetoRelativo o) {
        return Double.compare(this.valorRelativo, o.valorRelativo);
    }
}
