frase = str(input("Ingresa tu frase: "))
letra = str(input("Ingresa tu letra: "))

num = 0

for i in frase:
    if i == letra:
        num += 1

print("La letra {}, sale {} veces en la frase".format(letra, num))