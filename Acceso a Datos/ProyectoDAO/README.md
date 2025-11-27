**README - Proyecto: Biblioteca (DAO Pattern)**

**Estructura general y paquetes**

n dao

n dao.autor

n dao.libro

n dao.libro\_autor n dao.prestamo n dao.usuario

n model

n org.example

n service

**Clases e interfaces principales**

Autor AutorDAO AutorImpl BibliotecaService ConexionBD Libro LibroAutor LibroAutorDAO LibroAutorImpl LibroDAO LibroImpl

Main

Prestamo PrestamoDAO PrestamoImpl Usuario UsuarioDAO UsuarioImpl

**Modelos (package model)**

Autor Libro LibroAutor Prestamo Usuario

**DAOs (interfaces e implementaciones)**

El proyecto define interfaces DAO para Autor, Libro, Usuario, Prestamo y la relaci n LibroAutor, junto con sus implementaciones que interactœan con una base de datos MySQL (conector JDBC).

**Interfaces DAO detectadas:**

AutorDAO LibroAutorDAO LibroDAO PrestamoDAO UsuarioDAO

**Implementaciones DAO detectadas:**

AutorImpl LibroAutorImpl LibroImpl PrestamoImpl UsuarioImpl

**Capa de servicio: BibliotecaService**

La clase BibliotecaService orquesta operaciones de alto nivel: registrar autores/libros/usuarios/préstamos, listar y eliminar las como operaciones compuestas como registrar un libro junto con su autor (crea la relaci n en la tabla intermedia). La capa de servicio captura excepciones y realiza mensajes por consola cuando algo falla.

**Interfaz de consola (Main)**

El archivo ’Main’ provee un menú en consola (Scanner) con submenús para Autor, Libro, Préstamo y Usuario. Permite operaciones CRUD básicas y llama a BibliotecaService para ejecutar las acciones contra la base de datos. Formato de entrada para fechas en préstamos: ’YYYY-MM-DD’.

**Observaciones automáticas y recomendaciones**

La clase ConexionBD define un atributo estÆtico ’conexion’ y un mØtodo estÆtico getConexion(), pero la inicializaci n de la conexi n ocurre en el constructor ConexionBD(...). Si el resto del c digo llama a ConexionBD.getConexion() sin crear una instancia de ConexionBD, ’conexion’ puede ser null y provocar NullPointerException al usarla. Se recomienda proporcionar un mØtodo estÆtico de inicializaci n o mantener la conexi n gestionada internamente (p.ej. singleton).

Se detectan nombres de tabla/columnas inconsistentes relacionados con la relaci n libro-autor (p. ej. ’libroAutor’ vs ’libro\_autor’). Esto puede provocar SQLExceptions en tiempo de ejecuci n si la base de datos no tiene exactamente esas tablas/columnas. Asegœrese de unificar la convenci n de nombres

entre todas las clases DAO y el esquema de la base de datos.

En LibroImpl.updateLibro se actualiza œnicamente el campo ’titulo’ pero no ’isbn’. Si la intenci n es permitir actualizar ISBN tambiØn, hay que incluirlo en la sentencia UPDATE y en los parÆmetros. PrestamoImpl realiza LocalDate.parse(...) sobre las fechas recibidas; si la fecha no cumple el formato ISO ’YYYY-MM-DD’ lanzarÆ DateTimeParseException. Asegœrese de validar/normalizar el formato antes de llamar al servicio.

Uso mayoritariamente correcto de try-with-resources en los DAO para asegurar el cierre de

conexiones, sentencias y resultados.

**Fragmentos relevantes detectados (extractos)**

public class AutorImpl implements AutorDAO { @Override public void addAutor(Autor autor) { String sql =

"INSERT INTO autor (nombre) VALUES (?)"; try ( Connection conn = ConexionBD.getConexion();

PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN\_GENERATED\_KEYS) ) {

ps.setString(1, autor.getNombre()); ps.executeUpdate(); try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) autor.setId(rs.getInt(1)); } } catch (SQLException ex) { throw new RuntimeException(ex); } System.out.println("DAO: Autor

insertado ->

public class LibroImpl implements LibroDAO { @Override public void addLibro(Libro libro) throws SQLException {

String sql = "INSERT INTO libro (titulo, isbn) VALUES (?, ?)"; try ( Connection conn =

ConexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN\_GENERATED\_KEYS) ) { ps.setString(1, libro.getTitulo()); ps.setString(2,

libro.getIsbn()); ps.executeUpdate(); try (ResultSet rs =

ps.getGeneratedKeys()) {

if (rs.next()) libro.setId(rs.getInt(1)); } System.out.println("DAO: Libro

insertado -> " +

libro);

class ConexionBD { private static final String URL = "jdbc:mysql://localhost:3306/biblioteca"; private

static String USER = "root"; private static String PASSWORD = "123456789"; private static Connection

conexion; public ConexionBD(String usuario, String contra) { USER = usuario; PASSWORD = contra; try { conectar();

**Archivo fuente analizado**

/mnt/data/TrabajoDAOFin.txt

**C mo ejecutar / Notas finales**

\1) Crear la base de datos MySQL ’biblioteca’ y las tablas necesarias: autor, libro, libro\_autor (o libroAutor si usa esa convenci n), usuario y prestamo con columnas coherentes con las consultas SQL presentes en el c digo\. 2) Ajustar credenciales en ConexionBD o instanciar ConexionBD con las credenciales

deseadas antes de ejecutar la aplicaci n. 3) Compilar y ejecutar Main; la aplicaci n usa la consola para entradas. 4) Revisar y unificar nombres de tablas/columnas y la inicializaci n de la conexi n para evitar errores en tiempo de ejecuci n.
