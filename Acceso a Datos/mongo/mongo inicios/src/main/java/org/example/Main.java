package org.example;

import org.example.DAO.AmigoDAO;
import org.example.model.Amigos;

public class Main {
    static void main() {
        AmigoDAO amigos = new AmigoDAO();
/*        amigos.insertar(new Amigos(
                "sebastian",
                30,
                622190289
        ));

        amigos.insertar(new Amigos(
                "lucas",
                10,
                622444333
        ));

        System.out.println("Amigos agregados"); */

/*        amigos.listar();

        amigos.actualizar("sebastian", 2);
        System.out.println("Sebastian: cambio de edad a 2"); */

/*        amigos.listar();
        System.out.println("\n\n\n\n\n");
        amigos.eliminar("lucas");

        amigos.listar(); */

        amigos.listar();
    }
}
