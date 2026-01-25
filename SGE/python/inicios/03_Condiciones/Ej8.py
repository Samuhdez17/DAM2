puntos = float(input("Introduce tu puntuación: "))

if puntos == 0.0:
    nivel = "Inaceptable"
elif puntos == 0.4:
    nivel = "Aceptable"
elif puntos >= 0.6:
    nivel = "Meritorio"
else:
    nivel = ""

if nivel == "":
    print("Esta puntuación no es válida")

else:
    print("Tu nivel de rendimiento es %s" % nivel)
    print("Te corresponde cobrar %.2f€" % (puntos * 2400))