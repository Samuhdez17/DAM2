cesta = {}
continuar = True

while continuar:
    articulo = input('Introduce un artículo: ')
    precio = float(input('Introduce el precio de ' + articulo + ': '))

    cesta[articulo] = precio
    continuar = input('¿Quieres añadir artículos a la lista (si/no)? ').lower() == "si"

precioTotal = 0

print('Lista de la compra')
for articulo, precio in cesta.items():
    print(articulo, '\t', precio)

    precioTotal += precio

print('Coste total: ', precioTotal)