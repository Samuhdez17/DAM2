def getTel(ruta, cliente):
    try:
        fichero = open(ruta, 'r')
    except FileNotFoundError:
        return ('¡El fichero ' + ruta + ' no existe!\n')

    else:
        contenido = fichero.readlines()
        fichero.close()

        contenido = dict([tuple(line.split(',')) for line in contenido])

        if cliente in contenido:
            return contenido[cliente]
        else:
            return ('¡El cliente ' + cliente + ' no existe!\n')


def aniadirTel(ruta, cliente, tel):
    try:
        fichero = open(ruta, 'a')
    except FileNotFoundError:
        return ('¡El fichero ' + ruta + ' no existe!\n')

    else:
        fichero.write(cliente + ',' + tel + '\n')
        fichero.close()
        return ('El teléfono se ha añadido.\n')


def borrarTel(ruta, cliente):
    try:
        fichero = open(ruta, 'r')
    except FileNotFoundError:
        return ('¡El fichero ' + ruta + ' no existe!\n')

    else:
        contenido = fichero.readlines()
        fichero.close()

        contenido = dict([tuple(line.split(',')) for line in contenido])

        if cliente in contenido:
            del contenido[cliente]

            fichero = open(ruta, 'w')

            for name, telf in contenido.items():
                fichero.write(name + ',' + telf)

            fichero.close()

            return ('¡El cliente se ha borrado!\n')
        else:
            return ('¡El cliente ' + cliente + ' no existe!\n')


def crearFichero(ruta):
    import os

    if os.path.isfile(ruta):
        respuesta = input('El fichero ' + ruta + ' ya existe. ¿Desea vaciarlo (S/N)? ')

        if respuesta == 'N':
            return 'No se ha creado el fichero porque ya existe.\n'

    fichero = open(ruta, 'w')
    fichero.close()

    return 'Se ha creado el fichero.\n'


def menu():
    print('============================')
    print('1 - Consultar un teléfono')
    print('2 - Añadir un teléfono')
    print('3 - Eliminar un teléfono')
    print('4 - Crear el listín')
    print('0 - Salir')
    opcion = input('Introduzca una opcion: ')
    return opcion


def acciones():
    fichero = 'listin.txt'
    while True:
        opcion = menu()
        if opcion == '1':
            nombre = input('Introduce el nombre del cliente: ')
            print(getTel(fichero, nombre))
        elif opcion == '2':
            nombre = input('Introduce el nombre del cliente: ')
            tel = input('Introduce el teléfono del cliente: ')
            print(aniadirTel(fichero, nombre, tel))
        elif opcion == '3':
            nombre = input('Introduce el nombre del cliente: ')
            print(borrarTel(fichero, nombre))
        elif opcion == '4':
            print(crearFichero(fichero))
        else:
            break
    return


acciones()
