asignaturas = ["Gestion empresarial", "Acceso a datos", "Programacion de videojuegos", "Sostenibilidad"]

for i in range(len(asignaturas)-1, -1, -1):
    score = float(input("¿Qué nota has sacado en " + asignaturas[i] + "?"))

    if score >= 5:
        asignaturas.pop(i)

print("Tienes que repetir " + str(asignaturas))