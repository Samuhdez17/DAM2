dinero = int(input("Ingrese la cantidad de dinero: "))
interes = int(input("Ingrese el interes: "))
anios = int(input("Ingrese la cantidad de años: "))
dineroVariable = dinero

for i in range(anios):
    dineroVariable = dineroVariable * (1.00 + (interes / 100))
    print("En {} año/s tendras: {total:.2f}".format(i+1, total = float(dineroVariable)))