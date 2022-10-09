package br.com.uem.caixeiro;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstanceATSP {
    private int dimension;
    private int[][] arestas;

    public InstanceATSP(int dimension) {
        this.dimension = dimension;
        arestas = new int[dimension][dimension];
    }
}
