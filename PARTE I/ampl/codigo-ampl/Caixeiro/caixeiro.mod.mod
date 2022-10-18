set VERTICES ordered;

param posicao_y {VERTICES};
param posicao_x {VERTICES};

set PARES := {i in VERTICES, j in VERTICES: ord(i) < ord(j)};

param dist {(i,j) in PARES} := sqrt((posicao_y[i]-posicao_y[j])**2+(posicao_x[i]-posicao_x[j])**2);

var X {PARES} binary;

minimize z: sum {(i, j) in PARES} dist[i, j] * X[i, j];

subject to varre_todos_vertices {i in VERTICES}: sum{(i, j) in PARES} X[i, j] + sum {(j, i) in PARES} X[j, i] =2;

