package br.com.uem;

import lombok.Data;

@Data
public class MochilaMemorizada implements Mochila {
    private int[][] memo;
    private int[] pesos;
    private int[] valores;
    private int capacidade;
    private int qtd;

    private int ans;

    private long timeMillis;
    private long timeNano;

    private long numeroDeChamadas = 0;

    public MochilaMemorizada(Instance instance){
        this.qtd = instance.getQtd();
        this.capacidade = instance.getCapacidade();
        this.valores = instance.getValores();
        this.pesos = instance.getPesos();
    }

    private int opt(int pos, int capacidade){
        if(pos < 0 || capacidade == 0){
            return 0;
        }

        if(memo[pos][capacidade] != -1)
            return memo[pos][capacidade];

        numeroDeChamadas += 1;

        int optOne = opt(pos-1, capacidade);
        int ans;
        if(pesos[pos]>capacidade){
            ans = optOne;
        }else{
            ans = Math.max(optOne, opt(pos-1, capacidade-pesos[pos]) + valores[pos]);
        }

        memo[pos][capacidade] = ans;
        return ans;
    }

    @Override
    public InfoInstance getInfo() {
        InfoInstance instance = InfoInstance.builder()
                .ans(this.ans)
                .numeroDeChamadas(this.numeroDeChamadas)
                .timeMillis(this.timeMillis)
                .timeNano(this.timeNano)
                .numeroItens(this.qtd)
                .build();
        return instance;
    }

    @Override
    public void solve() {
        resetArrayMemo();

        long begin = System.currentTimeMillis();
        long beginNano = System.nanoTime();

        this.ans = this.opt(qtd-1, this.capacidade);

        long endNano = System.nanoTime();
        long end = System.currentTimeMillis();

        this.timeMillis = end-begin;
        this.timeNano = endNano - beginNano;

    }

    private void resetArrayMemo(){
        memo = new int[qtd][capacidade+1];

        for(int i = 0;i<qtd; i++){
            for(int j = 0;j<= capacidade; j++){
                memo[i][j] = -1;
            }
        }
    }
}
