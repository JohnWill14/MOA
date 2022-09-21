package br.com.uem;

import lombok.Data;

@Data
public class MochilaPedreragem implements Mochila {
    private int[] pesos;
    private int[] valores;
    private int capacidade;
    private int qtd;

    private int ans;

    private long timeMillis;
    private long timeNano;

    private long numeroDeChamadas = 0;

    public MochilaPedreragem(Instance instance){
        this.qtd = instance.getQtd();
        this.capacidade = instance.getCapacidade();
        this.valores = instance.getValores();
        this.pesos = instance.getPesos();
    }

    private int opt(int pos, int capacidade){
        if(pos < 0 || capacidade == 0){
            return 0;
        }

        numeroDeChamadas += 1;

        int optOne = opt(pos-1, capacidade);

        if(pesos[pos]>capacidade){
            return optOne;
        }

        return Math.max(optOne, opt(pos-1, capacidade-pesos[pos]) + valores[pos]);
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

        long begin = System.currentTimeMillis();
        long beginNano = System.nanoTime();

        this.ans = this.opt(qtd-1, this.capacidade);

        long endNano = System.nanoTime();
        long end = System.currentTimeMillis();

        this.timeMillis = end-begin;
        this.timeNano = endNano - beginNano;

    }
}
