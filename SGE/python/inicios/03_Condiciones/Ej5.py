edad = int(input("Ingrese su edad: "))
salario = float(input("Ingrese su salario mensual: "))

if edad > 16 and salario >= 1000:
    print("Tiene que tributar")
else:
    print("No tiene que tributar")