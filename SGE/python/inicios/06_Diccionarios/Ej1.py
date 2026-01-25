divisas = {'Euro': '€', 'Dollar': '$', 'Yen': '¥'}

divisa = input("Introduce una divisa: ")
print(divisas.get(divisa.title(), "La divisa no existe."))