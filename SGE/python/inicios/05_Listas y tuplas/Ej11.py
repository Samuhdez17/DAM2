a = (1, 2, 3)
b = (-1, 0, 2)
resultado = 0

for i in range(len(a)):
    resultado += a[i] * b[i]

print("El producto de los vectores" + str(a) + " y " + str(b) + " es " + str(resultado))