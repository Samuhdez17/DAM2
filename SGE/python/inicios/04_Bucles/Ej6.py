numero = int(input('Introduce un numero: '))

for altura in range(numero):
    for fila in range(altura + 1):
        print("*", end = "")
    print("")