
package patterns;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Titulo;
import modelos.TituloTipo2;

public class TituloTipo2Factory extends TituloFactory {

    @Override
    public Titulo[] createTitulos(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(jsonString, TituloTipo2[].class);
    }
    
}
