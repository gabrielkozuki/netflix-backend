
package controles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import modelos.Titulo;
import modelos.Usuario;
import patterns.TituloFactory;
import patterns.TituloTipo1Factory;
import patterns.TituloTipo2Factory;


public class TituloController {
     
    private final String API_URL = "https://api.themoviedb.org/3";
    private final String API_KEY = "038055597036fcb70b3cd616cc55c319";
    
    public enum TituloRota { 
        COMEDY, DOCUMENTARIES, NETFLIXORIGINALS, ROMANCES, TOPRATED, TRENDING;
    }
    
    private String getUrl(TituloRota tipo) { // Montagem da URL para requisição
        String url = "";
        
        switch (tipo) {
            case COMEDY:
                url = API_URL + "/discover/tv?language=pt-BR&api_key=" + API_KEY + "&with_genres=35";
                break;
            case DOCUMENTARIES:
                url = API_URL + "/discover/tv?language=pt-BR&api_key=" + API_KEY + "&with_genres=99";
                break;
            case NETFLIXORIGINALS:
                url = API_URL + "/discover/tv?language=pt-BR&api_key=" + API_KEY + "&with_networks=213";
                break;
            case ROMANCES:
                url = API_URL + "/discover/tv?language=pt-BR&api_key=" + API_KEY + "&with_genres=10749";
                break;
            case TOPRATED:
                url = API_URL + "/movie/top_rated?language=pt-BR&api_key=" + API_KEY;
                break;
            case TRENDING:
                url = API_URL + "/trending/all/week?language=pt-BR&api_key=" + API_KEY + "&with_genres=99";
                break;
        }
        
        return url;
    }
    
    private String getTitulosJson(String url) { // comunicação com a API TMDB
        try {
            HttpResponse <JsonNode> httpResponse;
            
            httpResponse = Unirest.get(url).asJson();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(httpResponse.getBody().toString());
            JsonObject jo = je.getAsJsonObject();
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(jo.get("results"));
            return jsonString;
            
        } catch (UnirestException ex) {
            Logger.getLogger(TituloController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private Titulo[] getTitulosList(String jsonString, TituloRota tipo) { // Recebe tipo de título da Factory
        TituloFactory factory;

        switch (tipo) {
            case TOPRATED:
            case TRENDING:
                factory = new TituloTipo2Factory();
                break;
            default:
                factory = new TituloTipo1Factory();
                break;
        }
        
        return factory.createTitulos(jsonString);
    }
    
    private String filtroIdade(Usuario user, String url) {
        if (user != null) {
            if (user.getIdade() >= 18) {
                url += "&include_adult=true";
            } else {
                url += "&include_adult=false";
            }
        }
        
        return url;
    }
    
    public String getTitulos(HttpSession session, TituloRota tipo) {
        Usuario user = (Usuario) session.getAttribute("usuario");
        String url = getUrl(tipo);
        
        url = filtroIdade(user, url);
        
        String jsonString = getTitulosJson(url);
        Titulo[] titulos = getTitulosList(jsonString, tipo);
        
        // manipulacao dos titulos...
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String res = gson.toJson(titulos);
        return res;
    }
    
}
