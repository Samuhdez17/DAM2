datosClientes = "nif;nombre;email;teléfono;descuento 01234567A;Lucas Fernandes;lucasfernandes@gmail.com;123123123;12.5 1231232R;Raul Ruiz;raul50@gmail.com;662205463;8"

listaClientes = datosClientes.split(' ')

clientes = {}

columnas = listaClientes[0].split(';')

for i in listaClientes[1:]:
    cliente = {}

    datos = i.split(';')

    for j in range(1, len(columnas)):
        if columnas[j] == 'descuento':
            datos[j] = float(datos[j])

        cliente[columnas[j]] = datos[j]

    clientes[datos[0]] = cliente

print(clientes)