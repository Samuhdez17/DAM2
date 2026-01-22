clientes = {}
opcion = ''

while opcion != '0':
    if opcion == '1':
        nif = input('Introduce NIF del cliente: ')
        nombre = input('Introduce el nombre del cliente: ')
        direccion = input('Introduce la dirección del cliente: ')
        telefono = input('Introduce el teléfono del cliente: ')
        email = input('Introduce el correo electrónico del cliente: ')
        vip = input('¿Es un cliente preferente (S/N)? ').upper()

        cliente = {'nombre':nombre, 'dirección':direccion, 'teléfono':telefono, 'email':email, 'preferente':vip=='S'}
        clientes[nif] = cliente

    if opcion == '2':
        nif = input('Introduce NIF del cliente: ')

        if nif in clientes:
            del clientes[nif]

        else:
            print('No existe el cliente con el nif', nif)

    if opcion == '3':
        nif = input('Introduce NIF del cliente: ')

        if nif in clientes:
            print('NIF:', nif)

            for clave, datos in clientes[nif].items():
                print(clave.title() + ':', datos)

        else:
            print('No existe el cliente con el nif', nif)

    if opcion == '4':
        print('Lista de clientes')

        for clave, datos in clientes.items():
            print(clave, datos['nombre'])

    if opcion == '5':
        print('Lista de clientes preferentes')

        for clave, datos in clientes.items():
            if datos['preferente']:
                print(clave, datos['nombre'])

    opcion = input('Menú de opciones\n(1) Añadir cliente\n(2) Eliminar cliente\n(3) Mostrar cliente\n(4) Listar clientes\n(5) Listar clientes preferentes\n(0) Salir\nElige una opción:')
