numero = int(input("Ingrese un numero: "))
orden = ""
for i in range(numero):
    if i % 2 != 0:
        if i != numero and i != 1:
            orden = orden + " ,"

        orden = orden + str(i)

print(orden[::-1])