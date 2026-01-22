palabras = input("Introduce la lista de palabra-traduccion con el siguiente formato: <palabra>:<traduccion>, ....: ")
diccionario = {}

for pareja in palabras.split(','):
    palabra, traduccion = pareja.split(':')
    diccionario[palabra] = traduccion

frase = str(input("Diccionario actualizado \nEscribe una frase en español: "))

for i in frase.split(' '):
    if i in diccionario:
        print(diccionario[i], end = " ")

    else:
        print(i, end = " ")
