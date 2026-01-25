def diccionario(frase):
    return {palabra: len(palabra) for palabra in frase.split()}

print(diccionario("Habichuelas en almíbar"))