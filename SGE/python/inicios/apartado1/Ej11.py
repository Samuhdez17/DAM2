dinero = int(input("Ingrese la cantidad de dinero"))
interes = 1.04
dineroVariable = round(dinero * interes)
print("El primer año tendras: " + str(dineroVariable))
dineroVariable = round(dineroVariable * interes)
print("El segundo: " + str(dineroVariable))
dineroVariable = round(dineroVariable * interes)
print("Y el tercero: " + str(dineroVariable))