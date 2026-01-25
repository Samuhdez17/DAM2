numero = int(input("Ingrese un numero: "))
primo = True

for i in range(2, numero):
    if numero % i == 0:
        primo = False
        break

if primo:
    print("El numero {} es primo".format(numero))

else:
    print("El numero {}, no es primo".format(numero))
