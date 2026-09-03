package primerTP;
import java.util.ArrayList;

public class BibliotecaLibro {
    static class Biblioteca {
        private String nombre;
        private String direccion;
        private ArrayList<Libro> libros;

        public Biblioteca(String nombre,String direccion){
            this.nombre=nombre;
            this.direccion=direccion;
            this.libros=new ArrayList<>();
        }

        public String getNombre(){return nombre;}
        public void setNombre(String nombre){this.nombre=nombre;}
        public String getDireccion(){return direccion;}
        public void setDireccion(String direccion){this.direccion=direccion;}
        public ArrayList<Libro> getLibros(){return new ArrayList<>(libros);}

        public boolean agregarLibro(Libro libro){
            if(libro==null||buscarPorIsbn(libro.getIsbn())!=null)return false;
            libros.add(libro);
            return true;
        }

        public Libro buscarPorIsbn(String isbn){
            for(Libro libro:libros){
                if(libro.getIsbn().equals(isbn))return libro;
            }
            return null;
        }

        public boolean retirarLibro(String isbn){
            Libro libro=buscarPorIsbn(isbn);
            if(libro==null)return false;
            libros.remove(libro);
            return true;
        }

        public int contarLibros(){return libros.size();}

        public Libro obtenerLibroMasExtenso(){
            if(libros.isEmpty())return null;
            Libro mayor=libros.get(0);
            for(Libro libro:libros)if(libro.getPaginas()>mayor.getPaginas())mayor=libro;
            return mayor;
        }

        public void mostrarCatalogo(){
            System.out.println("========= Catálogo de la biblioteca "+nombre+" =========");
            for(Libro libro:libros)System.out.println(libro);
            System.out.println("=======================================================");
        }
    }

    static class Libro {
        private String isbn;
        private String titulo;
        private String autor;
        private int paginas;

        public Libro(String isbn,String titulo,String autor,int paginas){
            this.isbn=isbn;
            this.titulo=titulo;
            this.autor=autor;
            this.paginas=paginas;
        }

        public String getIsbn(){return isbn;}
        public void setIsbn(String isbn){this.isbn=isbn;}
        public String getTitulo(){return titulo;}
        public void setTitulo(String titulo){this.titulo=titulo;}
        public String getAutor(){return autor;}
        public void setAutor(String autor){this.autor=autor;}
        public int getPaginas(){return paginas;}
        public void setPaginas(int paginas){this.paginas=paginas;}

        @Override
        public String toString(){return "Libro [ISBN="+isbn+", título="+titulo+", autor="+autor+", páginas="+paginas+"]";}
    }

    public static void main(String[] args){
        Libro libro1=new Libro("978-3-16-148410-0","El Quijote","Cervantes",500);
        Libro libro2=new Libro("978-0-14-044913-6","Cien años de soledad","García Márquez",400);
        Libro libro3=new Libro("978-0-452-28423-4","1984","Orwell",328);
        Biblioteca bibliotecaA=new Biblioteca("Central","Av. Siempreviva 123");
        Biblioteca bibliotecaB=new Biblioteca("Sucursal Norte","Calle Falsa 456");

        bibliotecaA.agregarLibro(libro1);
        bibliotecaA.agregarLibro(libro2);
        bibliotecaA.agregarLibro(libro3);

        System.out.println("--- Buscar por ISBN ---");
        System.out.println(bibliotecaA.buscarPorIsbn("978-3-16-148410-0"));

        System.out.println("\n--- Retirar libro ---");
        bibliotecaA.retirarLibro("978-0-14-044913-6");
        bibliotecaA.mostrarCatalogo();

        System.out.println("\n--- Cantidad de libros ---");
        System.out.println(bibliotecaA.contarLibros());

        System.out.println("\n--- Libro más extenso ---");
        System.out.println(bibliotecaA.obtenerLibroMasExtenso());

        System.out.println("\n--- Trasladar 1984 de A a B ---");
        if(bibliotecaA.retirarLibro("978-0-452-28423-4")){
            bibliotecaB.agregarLibro(libro3);
            System.out.println("Trasladado.");
        }

        System.out.println("\n--- Catálogo A ---");
        bibliotecaA.mostrarCatalogo();

        System.out.println("\n--- Catálogo B ---");
        bibliotecaB.mostrarCatalogo();

        System.out.println("\n--- Libro3 sigue existiendo ---");
        System.out.println(libro3);
    }
}