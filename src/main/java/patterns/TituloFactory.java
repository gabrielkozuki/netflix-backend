
package patterns;

import modelos.Titulo;

public abstract class TituloFactory {
    
    public abstract Titulo[] createTitulos(String jsonString);
}