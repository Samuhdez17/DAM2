from statistics import mean, stdev

def calcularAtipico(lista):
    media = mean(lista)
    desviacion = stdev(lista)

    def esAtipico(numero):
        puntuacion = (numero - media) / desviacion
        return (puntuacion < -3) or (puntuacion > 3)

    return esAtipico

def listarNumeros(lista):
    return list(filter(calcularAtipico(lista), lista))

print(listarNumeros([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1000]))
