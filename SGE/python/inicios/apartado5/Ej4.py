numsGanadores = []

while True:
    numeroGanador = input("Ingrese un numero ganador (salir para terminar): ")
    if numeroGanador == "salir":
        break

    else:
        numsGanadores.append(int(numeroGanador))

numsGanadores.sort()
print("Numeros ganadores:", numsGanadores)