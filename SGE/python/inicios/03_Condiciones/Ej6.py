nombre = str(input("Ingrese su nombre: ").upper())
sexo = str(input("Ingrese su sexo (Masculino/Feminino): ").upper())

if (sexo[0] == "M" and nombre[0] > "N"
        or sexo[0] == "F" and nombre[0] < "M"):
    print("Tu grupo es el A")

else:
    print("Tu grupo es el B")