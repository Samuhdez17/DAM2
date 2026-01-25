def listarNumeros(fnEsPar, lista):
    l = []
    for i in lista:
        if fnEsPar(i):
            l.append(i)
    return l

def esPar(n):
    return n % 2 == 0

print(listarNumeros(esPar, [1, 2, 3, 4, 5, 6]))