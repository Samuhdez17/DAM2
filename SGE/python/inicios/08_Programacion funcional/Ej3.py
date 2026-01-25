def listarNumeros(fnElevar, lista):
    l = []
    for i in lista:
        l.append(fnElevar(i))
    return l

def elevarAlCuadrado(n):
    return n * n

print(listarNumeros(elevarAlCuadrado, [1, 2, 3, 4]))
