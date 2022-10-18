#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>


int mochila(int pos, int w, int* pesos,int* valores);

int main(int argc, char *argv[]) {
    FILE *sorce;
    int result;

    sorce = fopen(argv[1], "rt");

    if (sorce == NULL){
        printf("Problemas na LEITURA do arquivo\n");
        return 0;
    }

    int qtd, capacidade;

    fscanf(sorce, "%d %d", &qtd, &capacidade);

    int valores[qtd];
    int pesos[qtd];

    
    for(int i=0; i<qtd; i++){
        int v1, v2;
        fscanf(sorce, "%d %d", &v1, &v2);
       
        valores[i] = v1;
        pesos[i] = v2; 
    }
    
    fclose(sorce);
 
    struct timeval stop, start;
    gettimeofday ( &start, NULL );

    int opt = mochila(qtd-1, capacidade, pesos, valores);
    gettimeofday ( &stop, NULL );
    printf("Valor max: %d\n", opt);
    printf("Tempo: %lu milliseconds\n", (stop.tv_usec - start.tv_usec)/1000);

    return 0;
}


int mochila(int pos, int w, int* pesos,int* valores){
    if(pos < 0 || w == 0){
        return 0;
    }

    int valor1 = mochila(pos-1, w, pesos, valores);
    if(pesos[pos] > w){
        return valor1;
    }

    int valor2 = mochila(pos-1, w-pesos[pos], pesos, valores) + valores[pos];

    return valor1>valor2 ? valor1 : valor2;
}