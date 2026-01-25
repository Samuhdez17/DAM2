frutas = {'plátano':1.35, 'manzana':0.8, 'pera':0.85, 'naranja':0.7}

fruta = input('¿Qué fruta quieres? ').lower()
kg = float(input('¿Cuántos kilos quieres? '))

if fruta in frutas:
    print(kg, 'kilos de', fruta, 'valen', frutas[fruta] * kg, '€')
else: 
    print("La fruta", fruta, "no está disponible.")