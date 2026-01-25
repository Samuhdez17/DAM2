print("Selecciona el tipo de pizza\n\t1. Vegetariana\n\t2. No Vegetariana")
opcion = input()

if opcion == "1":
    print("Ingredientes para pizza vegetariana: \n\t1. Pimiento \n\t2. Tofu")
    opcion = input("Seleccione un ingrediente")

    if opcion == "1":
        print("Pizza con tomate, mozarella y pimiento")
    else:
        print("Pizza vegetariana con mozarella, tomate y tofu")

else:
    print("Ingredientes para pizza no vegetariana: \n\t1. Perperoni \n\t2. Jamon \n\t3. Salmon")
    opcion = input("Seleccione un ingrediente")

    if opcion == "1":
        print("Pizza no vegetariana con tomate, mozarella y perperoni")
    elif opcion == "2":
        print("Pizza no vegetariana con mozarella, tomate y jamon")
    else:
        print("Pizza no vegetariana con mozarella, tomate y salmon")
