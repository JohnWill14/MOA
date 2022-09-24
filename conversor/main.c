#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
    FILE *sorce, *destiny;
    int result;

    printf("%s\n", argv[2]);

    sorce = fopen(argv[1], "rt");
    destiny = fopen(argv[2], "at");

    if (sorce == NULL || destiny == NULL){
        printf("Problemas na CRIACAO do arquivo\n");
        return 0;
    }

    int qtd, capacidade;

    fprintf(destiny, "set ITEM := ");
    fscanf(sorce, "%d %d", &qtd, &capacidade);
    
    for(int i=1; i<=qtd; i++){
        fprintf(destiny, "%d", i);
        if(i!=qtd){
            fprintf(destiny, " ");
        }else{
            fprintf(destiny, ";\n");
        }
    }

    fprintf(destiny, "\nparam: valor peso	    := \n");
    
    int cont = 1; 
    for(int i=1; i<=qtd; i++){
        int v1, v2;
        fscanf(sorce, "%d %d", &v1, &v2);
        fprintf(destiny, "%d %d %d\n", cont, v1, v2);
        cont+=1;
    }
    fprintf(destiny, ";\n");

    fprintf(destiny, "param capacidade	:= %d ;", capacidade);
    
    fclose(sorce);
    fclose(destiny);
    return 0;
}