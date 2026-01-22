asignaturas = ["Gestion empresarial", "Acceso a datos", "Programacion de videojuegos", "Sostenibilidad"]

notas = []
for asignatura in asignaturas:
    nota = input("¿Qué nota has sacado en " + asignatura + "?")
    notas.append(nota)
for i in range(len(asignaturas)):
    print("En " + asignaturas[i] + " has sacado " + notas[i])