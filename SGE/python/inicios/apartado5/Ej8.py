palabra = input("Introduce una palabra: ")
palabraInversa = palabra[::-1]

if palabra == palabraInversa:
    print("Es un palíndromo")
else:
    print("No es un palíndromo")