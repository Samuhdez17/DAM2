def calcularRaizCuadrada(listaNums):
    list = []
    for i in listaNums:
        list.append(i ** 2)
    return list

def calcularMedia(listaNums):
    stat = {}
    stat['media'] = sum(listaNums) / len(listaNums)
    stat['varianza'] = sum(calcularRaizCuadrada(listaNums)) / len(listaNums) - stat['media'] ** 2
    stat['desviacion'] = stat['varianza'] ** 0.5
    return stat

print(calcularMedia([1, 2, 3, 4, 5]))
print(calcularMedia([2.3, 5.7, 6.8, 9.7, 12.1, 15.6]))