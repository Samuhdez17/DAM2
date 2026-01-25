cantidad = float(input("Indica la cantidad a invertir "))
interes = float(input("Indica el interes anual"))
años = int(input("Indica los años que quieres estar invirtiendo"))
print("Capital final: " + str(round(cantidad * (interes / 100 + 1) ** años, 2)))