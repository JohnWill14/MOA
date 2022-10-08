package br.com.uem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InfoInstance {
    private int ans;
    private long numeroDeChamadas;
    private long timeMillis;
    private long timeNano;
    private int numeroItens;

}
