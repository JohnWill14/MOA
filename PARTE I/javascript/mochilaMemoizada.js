// let valores = [4, 6, 10, 12, 13];
// let pesos = [11, 2, 4, 6, 7];

// valores = [
//   20, 91, 72, 90, 46, 55, 8, 35, 75, 61, 15, 77, 40, 63, 75, 29, 75, 17, 78, 40,
//   44,
// ];

// pesos = [
//   879, 84, 83, 43, 4, 44, 6, 82, 92, 25, 83, 56, 18, 58, 14, 48, 70, 96, 32, 68,
//   92,
// ];

const valores = [];
const pesos = [];
const memo = [];

// let W;

const fs = require('fs');

// const W = 11;

function opt(i, W) {
  if (i < 0 || W == 0) {
    return 0;
  }

  if (memo[i][W] != null) {
    return memo[i][W];
  }

  let yuri = opt(i - 1, W);

  if (pesos[i] > W) {
    return yuri;
  } else {
    yuri = Math.max(yuri, opt(i - 1, W - pesos[i]) + valores[i]);
  }

  memo[i][W] = yuri;
  return yuri;
}

const data = fs.readFileSync('./instances/large_scale/knapPI_1_1000_1000_1', 'utf8');
// if (err) throw err;
const [firstLine] = data.split('\r');
let [qtd, W] = firstLine.split(' ');

const lines = data.split('\r');

lines.forEach(function (line) {
  const [n1, n2] = line.split(' ');
  valores.push(Number(n1));
  pesos.push(Number(n2));
});

for (let i = 0; i < qtd; i++) {
  memo.push([]);
  for (let j = 0; j <= W; j++) {
    memo[i].push(null);
  }
}

const begin = new Date().getMilliseconds();
const valorMaximo = opt(qtd - 1, W);
const end = new Date().getMilliseconds();
console.log(`Máximo: ${valorMaximo}`);
console.log(`Tempo de execução em milissegundos: ${end - begin}`);
