import pandas as pd
import matplotlib.pyplot as plt


def diagrama(ventas, tipo):
    grafica = {'lineas': 'line', 'barras': 'bar', 'sectores': 'pie', 'area': 'area'}

    fig, ax = plt.subplots()
    ventas.plot(kind=grafica[tipo], ax=ax)
    plt.title('Evolución del número de ventas')

    return ax

df_ventas = pd.Series([1200, 840, 1325, 1280, 1500], index=[2000, 2001, 2002, 2003, 2004])
diagrama(df_ventas, 'lineas')
plt.show()
diagrama(df_ventas, 'area')
plt.show()
diagrama(df_ventas, 'barras')
plt.show()
diagrama(df_ventas, 'sectores')
plt.show()