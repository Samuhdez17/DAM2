numero = int(input("Indica un numero: "))

for altura in range(1, numero + 1, 2):
    for i in range (altura, 0, -2):
        print(i, end = " ")

    print("")