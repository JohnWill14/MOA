set ITEM;					 # Conjunto de itens, cada item contendo um peso e um valor
param peso {ITEM};			
param valor {ITEM};			
param capacidade > 0;

var item {i in ITEM} binary; # 1 se item é colocado na mochila, 0 caso contrário 

#Objetivo: descobrir quais itens estão na mochila
maximize z: sum {i in ITEM} item[i] * valor[i];

#capacidade maxima da mochila 
subject to Capacidade: sum {i in ITEM} item[i] * peso[i] <= capacidade;
