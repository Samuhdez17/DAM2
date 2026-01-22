numeros = input("Introduce una muestra de números separados por comas: ").split(',')

n = len(numeros)

for i in range(n):
    numeros[i] = int(numeros[i])
numeros = tuple(numeros)
sumaMedia = 0
sumaDesviacion = 0
for i in numeros:
    sumaMedia += i
    sumaDesviacion += i ** 2
media = sumaMedia / n
desviacion = (sumaDesviacion / n - media ** 2) ** (1 / 2)
print('La media es', media, ', y la desviación típica es', desviacion)