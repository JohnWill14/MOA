package br.com.uem.mochila;

import br.com.uem.InfoInstance;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class MochilaComMelhoramento implements Mochila {
    private int[][] memo;
    private int[] pesos;
    private int[] valores;
    private int capacidade;
    private int qtd;

    private int ans;

    private long timeMillis;
    private long timeNano;

    private long numeroDeChamadas = 0;

    public MochilaComMelhoramento(Instance instance){
        this.qtd = instance.getQtd();
        this.capacidade = instance.getCapacidade();
        this.valores = instance.getValores();
        this.pesos = instance.getPesos();
    }

    private int opt(){

        ValorObjetoRelativo[] valorRelativo = new ValorObjetoRelativo[valores.length];

        for(int i=0 ;i<valores.length; i++){
            valorRelativo[i] = new ValorObjetoRelativo(pesos[i], valores[i]);
        }

        Arrays.sort(valorRelativo, Collections.reverseOrder());

        List<ValorObjetoRelativo> objetosSelecionados = new ArrayList<>();

        int pesoObjetos = 0, cont = 0;

        while (pesoObjetos<this.capacidade&&cont<this.qtd){
            if(valorRelativo[cont].getPeso()+pesoObjetos<=this.capacidade){
                pesoObjetos += valorRelativo[cont].getPeso();
                objetosSelecionados.add(valorRelativo[cont]);
            }
            cont++;
        }

        Set<ValorObjetoRelativo> collect = Arrays.stream(valorRelativo)
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(ValorObjetoRelativo::getPeso))
                ));

        collect.removeAll(objetosSelecionados);

        objetosSelecionados = melhoramento(collect, objetosSelecionados);


        return getAns(objetosSelecionados);
    }

    private List<ValorObjetoRelativo> melhoramento( Set<ValorObjetoRelativo> objetosForaDaMochila,
                                                   List<ValorObjetoRelativo> objetosSelecionados){
        int totalItens = objetosForaDaMochila.size()+objetosSelecionados.size();
        int total = getAns(objetosSelecionados);
        List<ValorObjetoRelativo> novaResposta = new LinkedList<>(objetosSelecionados);
        int pesoMochila = getSomaPeso(novaResposta);

        Collections.sort(novaResposta, Collections.reverseOrder());
        objetosForaDaMochila.removeAll(novaResposta);

        List<ValorObjetoRelativo> itensExcluidosForaDaMochila = new ArrayList<>();

        int cont = 0, i = 0;

        while (cont<novaResposta.size()){

            ValorObjetoRelativo valorObjetoRelativo = novaResposta.get(novaResposta.size() - (cont+1));
            pesoMochila -= valorObjetoRelativo.getPeso();
            novaResposta.remove(valorObjetoRelativo);

            while (!objetosForaDaMochila.isEmpty()){
                ValorObjetoRelativo objeto = objetosForaDaMochila.stream().findFirst().get();

                if( objeto.getPeso()+pesoMochila>this.capacidade){
                    break;
                }

                novaResposta.add(objeto);
                pesoMochila += objeto.getPeso();
                objetosForaDaMochila.remove(objeto);
                itensExcluidosForaDaMochila.add(objeto);
            }

            if(getAns(novaResposta)>total&&getSomaPeso(novaResposta)<=this.capacidade){
                total = getAns(novaResposta);
                objetosSelecionados = new ArrayList<>(novaResposta);
                objetosForaDaMochila.add(valorObjetoRelativo);
                cont = 0;
            }else{
                if(itensExcluidosForaDaMochila.isEmpty()){
                    continue;
                }
                novaResposta = new LinkedList<>(objetosSelecionados);
                Collections.sort(novaResposta, Collections.reverseOrder());
                objetosForaDaMochila.addAll(itensExcluidosForaDaMochila);
                cont++;
            }
        }
        return objetosSelecionados;
    }
    private int getAns(List<ValorObjetoRelativo> objetosSelecionados){
        return objetosSelecionados.stream()
                .map(ValorObjetoRelativo::getValor)
                .reduce(Integer::sum)
                .get();
    }

    private int getSomaPeso(List<ValorObjetoRelativo> objetosSelecionados){
        return objetosSelecionados.stream()
                .map(ValorObjetoRelativo::getPeso)
                .reduce(Integer::sum)
                .get();
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

        this.ans = this.opt();

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
