import time
import sys

valores = []
pesos = []
memo = []

def opt(i, w):
  if i < 0 or w == 0:
    return 0

  if memo[i][w] != None:
    return memo[i][w]

  optOne = opt(i-1, w)
  if pesos[i] > w:
    ans = optOne
  else: 
    ans = max(optOne, opt(i -1, w-pesos[i]) + valores[i])

  memo[i][w] = ans
  return ans

with open("./large_scale/knapPI_1_1000_1000_1") as fileIn:
  qtd, w = fileIn.readline().split()
  qtd = int(qtd)
  w = int(w)
  for line in fileIn:
    n1, n2 = (int(s) for s in line.split())
    valores.append(n1)
    pesos.append(n2)

for i in range(qtd):
  memo.append([])
  for j in range(w+1):
    memo[i].append(None)

sys.setrecursionlimit(5000)
begin = time.time()
valor_maximo = opt(len(valores) - 1, w)
end = time.time()

print('Máximo: ' + str(valor_maximo))
print('Tempo de execução em milissegundos: ' + str(end - begin))