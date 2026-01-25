def nota(cifra):
    cifra = cifra.replace(',', '.')
    return float(cifra)


def notas(ruta):
    try:
        fichero = open(ruta, 'r')
    except FileNotFoundError:
        print('El fichero no existe.')
        return

    lineas = fichero.readlines()
    fichero.close()

    claves = lineas[0][:-1].split(";")
    calificaciones = []

    for i in lineas[1:]:
        valores = i[:-1].split(";")
        alumno = {}

        for j in range(len(valores)):
            alumno[claves[j]] = valores[j]
        calificaciones.append(alumno)

    return calificaciones


def calcularNotaFinal(notas):
    def notaFinal(alumno):
        if alumno[
            'Ordinario1']:
            parcial1 = nota(alumno['Ordinario1'])
        elif alumno['Parcial1']:
            parcial1 = nota(alumno['Parcial1'])
        else:
            parcial1 = 0
        if alumno[
            'Ordinario2']:
            parcial2 = nota(alumno['Ordinario2'])
        elif alumno['Parcial2']:
            parcial2 = nota(alumno['Parcial2'])
        else:
            parcial2 = 0
        if alumno[
            'OrdinarioPracticas']:
            practicas = nota(alumno['OrdinarioPracticas'])
        elif alumno['Practicas']:
            practicas = nota(alumno['Practicas'])
        else:
            practicas = 0

        alumno['Final1'] = parcial1
        alumno['Final2'] = parcial2
        alumno['FinalPracticas'] = practicas
        alumno['NotaFinal'] = parcial1 * 0.3 + parcial2 * 0.3 + practicas * 0.4

        return alumno

    return list(map(notaFinal, notas))


def aprobadosYSuspensos(notas):
    aprobados = []
    suspensos = []

    for alumno in notas:
        if all([int(alumno['Asistencia'][:-1]) >= 75, alumno['Final1'] >= 4, alumno['Final2'] >= 4,
                alumno['FinalPracticas'] >= 4, alumno['NotaFinal'] >= 5]):
            aprobados.append(alumno['Apellidos'] + ', ' + alumno['Nombre'])
        else:
            suspensos.append(alumno['Apellidos'] + ', ' + alumno['Nombre'])

    return aprobados, suspensos


print(calcularNotaFinal(notas('calificaciones.csv')))
aprobados, suspensos = aprobadosYSuspensos(calcularNotaFinal(notas('calificaciones.csv')))
print('Lista de aprobados:', aprobados)
print('Lista de suspensos:', suspensos)
