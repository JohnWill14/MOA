import time

valores = []
pesos = []
# memo = []

def opt(i, w):
  if i < 0 or w == 0:
    return 0

  optOne = opt(i-1, w)
  if pesos[i] > w:
    return optOne

  return max(optOne, opt(i -1, w-pesos[i]) + valores[i])


with open("./large_scale/knapPI_1_100_1000_1") as fileIn:
  qtd, w = fileIn.readline().split()
  for line in fileIn:
    n1, n2 = (int(s) for s in line.split())
    valores.append(n1)
    pesos.append(n2)

# print(valores, pesos)

begin = time.time()
valor_maximo = opt(len(valores) - 1, int(w))
end = time.time()

print('Máximo: ' + str(valor_maximo))
print('Tempo de execução em milissegundos: ' + str(end - begin))