facturas = {}
cobrado = 0
pendiente = 0

opcion = ''
while opcion != 'T':
    if opcion == 'A':
        numFactura = input('Introduce el número de la factura: ')
        coste = float(input('Introduce el coste de la factura: '))

        facturas[numFactura] = coste
        pendiente += coste

    if opcion == 'P':
        numFactura = input('Introduce el número de la factura a pagar: ')
        coste = facturas.pop(numFactura, 0)

        cobrado += coste
        pendiente -= coste

    print('Recaudado:', cobrado)
    print('Pendiente de cobro: ', pendiente)
    opcion = input('¿Quieres añadir una nueva factura (A), pagarla (P) o salir (S)? ').upper()

