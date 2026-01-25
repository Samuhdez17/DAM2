a = int(input("Primer numero: "))
b = int(input("Segundo numero: "))

if b == 0:
    print("ERROR. No se puede dividir entre 0")
else:
    print("{} / {} = {}".format(a, b, a / b))