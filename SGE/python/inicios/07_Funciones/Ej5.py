def areaDelCirculo(radius):
    pi = 3.1415
    return pi * radius ** 2

def volumenDeCilindro(radio, altura):
    return areaDelCirculo(radio) * altura

print(volumenDeCilindro(3, 5))