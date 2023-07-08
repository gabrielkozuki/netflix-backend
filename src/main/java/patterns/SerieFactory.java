
package patterns;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Titulo;
import modelos.Serie;

public class SerieFactory extends TituloFactory {

    @Override
    public Titulo[] createTitulos(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(jsonString, Serie[].class);
    }
    
}
