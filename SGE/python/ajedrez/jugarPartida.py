def partida_ajedrez(nombre_fichero):
    # Mapeo de columnas
    columnas = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']

    # Abrir archivo para escribir movimientos UCI
    f = open(nombre_fichero, 'w', encoding='utf-8')

    movimiento = 0
    while True:
        continuar = input('Deseas hacer otro movimiento (s/n): ')

        if continuar != 's':
            break
        else:
            # Pedir coordenadas igual que antes
            origen = input('Introduce coordenadas iniciales (fila,col): ').split(',')
            destino = input('Introduce coordenadas finales (fila,col): ').split(',')

            # Convertir a enteros
            fila_origen = int(origen[0])
            col_origen = int(origen[1])
            fila_destino = int(destino[0])
            col_destino = int(destino[1])

            # Crear movimiento UCI: letra_columna + número_fila
            uci_move = f"{columnas[col_origen - 1]}{fila_origen}{columnas[col_destino - 1]}{fila_destino}"

            # Escribir solo el movimiento UCI
            f.write(uci_move + '\n')

            movimiento += 1

    f.close()
    return


partida_ajedrez('partida4.txt')