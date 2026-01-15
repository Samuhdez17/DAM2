print("Indica peso")
peso = input()
print("Indica altura")
altura = input()
if altura.__contains__(","):
    altura = altura.replace(",",".")
imc = round(float(peso)/float(altura)**2,2)
print("Tu indice de masa corporal es " + str(imc))

