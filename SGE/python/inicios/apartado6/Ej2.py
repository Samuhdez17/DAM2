nombre = input('¿Cómo te llamas? ')
edad = input('¿Cuántos años tienes? ')
direccion = input('¿Cuál es tu dirección? ')
telefono = input('¿Cuál es tu número de teléfono? ')

persona = {'nombre': nombre, 'edad': edad, 'direccion': direccion, 'telefono': telefono}

print(persona['nombre'], ' -> edad:', persona['edad'], 'años; direccion:', persona['direccion'], '; teléfono:', persona['telefono'])