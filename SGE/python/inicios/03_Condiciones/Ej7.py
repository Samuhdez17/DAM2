renta = float(input("Ingrese su renta: "))

if renta < 10000:
    tipo = 5

elif renta < 20000:
    tipo = 15

elif renta < 35000:
    tipo = 20

elif renta < 60000:
    tipo = 30

else:
    tipo = 45

print("El tipo del renta es: ", tipo)