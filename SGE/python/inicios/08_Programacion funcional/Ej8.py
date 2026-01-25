def calificacion(nota):
    if nota < 5:
        return 'IN'
    elif nota < 7:
        return 'AP'
    elif nota < 9:
        return 'NT'
    elif nota < 10:
        return 'SB'
    else:
        return 'MH'

def estaAprobado(asignatura):
    return (asignatura[1] >= 5)


def asignarNota(nota):
    passed = dict(filter(estaAprobado, nota.items()))
    subjects = map(str.upper, passed.keys())
    grades = map(calificacion, passed.values())
    return dict(zip(subjects, grades))

print(asignarNota({'Matemáticas':6.5, 'Física':5, 'Química':3.4, 'Economía':8.2, 'Historia':9.7, 'Programación':10}))