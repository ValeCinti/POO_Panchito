package primerTP;
import java.util.ArrayList;




public class BibliotecaLibro {
    static class Biblioteca {
        private String nombre;
        private String direccion;
        private ArrayList<Libro> libros;

        public Biblioteca(String nombre, String direccion) {
            this.nombre = nombre;
            this.direccion = direccion;
            this.libros = new ArrayList<>();
        }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }

        public ArrayList<Libro> getLibros() { return libros; }
        public void setLibros(ArrayList<Libro> libros) { this.libros = libros; }

        public void agregarLibro(Libro libro) { libros.add(libro); }
        public void eliminarLibro(Libro libro) { libros.remove(libro); }

        public int contarLibros(){
            int CantidadDeLibros = libros.size();
            return CantidadDeLibros;
        }

        public Libro obtenerlibromasextenso(){
            Libro librosmasextenso = libros.getLast();
            for ( Libro l : libros){
                if(l.getPaginas() >= librosmasextenso.getPaginas()){
                    librosmasextenso=l;
                }
            }
            return librosmasextenso;
        }


        public void MostarCatalogo(){
            System.out.println("=========Catalogo De la Biblioteca "+nombre+"============");
            for( Libro l : libros){
                System.out.println(l.toString());
            }
            System.out.println("==========================================================");
        }


        public Libro Buscarlibroporisbn(String isbn){
            for (Libro l : libros){
                if(l.getIsbn().equals(isbn)){
                    return l;
                }
            }
            return null;
        }


        public boolean Retirarlibro(String isbn){
            Libro l=Buscarlibroporisbn(isbn);
            if (l!=null){
                libros.remove(l);
                System.out.println("El libro "+l.getTitulo()+"fue retirado con exito");
                return true;
            }else{
                System.out.println("El libro que buscaba retirar no se encuentra disponible");
                return false;
            }
        }



        @Override
        public String toString() {
            return "Biblioteca [nombre=" + nombre + ", direccion=" + direccion + ", libros=" + libros.size() + "]";
        }
    }

    static class Libro {
        private String isbn;
        private String titulo;
        private String autor;
        private int paginas;

        // Constructor
        public Libro(String isbn, String titulo, String autor, int paginas) {
            this.isbn = isbn;
            this.titulo = titulo;
            this.autor = autor;
            this.paginas = paginas;
        }

        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }

        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }

        public String getAutor() { return autor; }
        public void setAutor(String autor) { this.autor = autor; }

        public int getPaginas() { return paginas; }
        public void setPaginas(int paginas) { this.paginas = paginas; }

        @Override
        public String toString() {
            return "Libro [ISBN=" + isbn + ", título=" + titulo + ", autor=" + autor + ", páginas=" + paginas + "]";
        }
    }

    static void main() {
        Libro libro1 = new Libro("978-3-16-148410-0", "El Quijote", "Cervantes", 500);
        Libro libro2 = new Libro("978-0-14-044913-6", "Cien años de soledad", "García Márquez", 400);
        Libro libro3 = new Libro("978-0-452-28423-4", "1984", "Orwell", 328);

        Biblioteca bA = new Biblioteca("Central", "Av. Siempreviva 123");
        Biblioteca bB = new Biblioteca("Sucursal Norte", "Calle Falsa 456");

        bA.agregarLibro(libro1);
        bA.agregarLibro(libro2);
        bA.agregarLibro(libro3);

        System.out.println("--- Buscar por ISBN ---");
        System.out.println(bA.Buscarlibroporisbn("978-3-16-148410-0"));

        System.out.println("\n--- Retirar libro ---");
        bA.Retirarlibro("978-0-14-044913-6");
        bA.MostarCatalogo();

        System.out.println("\n--- Contar libros: " + bA.contarLibros());

        System.out.println("\n--- Libro más extenso ---");
        System.out.println(bA.obtenerlibromasextenso());

        System.out.println("\n--- Trasladar 1984 de A a B ---");
        if (bA.Retirarlibro("978-0-452-28423-4")) {
            bB.agregarLibro(libro3);
            System.out.println("Trasladado.");
        }

        System.out.println("\n--- Catálogo A ---");
        bA.MostarCatalogo();
        System.out.println("\n--- Catálogo B ---");
        bB.MostarCatalogo();

        System.out.println("\n--- libro3 sigue existiendo ---");
        System.out.println(libro3);

    }
}
