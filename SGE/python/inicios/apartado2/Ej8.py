precio = input("Indica un precio: ")
precio = precio.replace(".",",")
print("Numero entero: " + precio[:precio.find(",")] + " Centimos: " + precio[precio.find(",") + 1:])