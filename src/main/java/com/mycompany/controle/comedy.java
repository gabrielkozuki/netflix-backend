/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 //https://software.dzhuvinov.com/cors-filter-installation.html

package com.mycompany.controle;

import com.google.gson.*;
import controles.UsuarioController;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Application;
import modelos.Erro;
import modelos.Titulo;
import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gutol
 */
public class comedy extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpResponse <JsonNode> httpResponse;
        try {
            httpResponse = Unirest.get("https://api.themoviedb.org/3/discover/tv?api_key=038055597036fcb70b3cd616cc55c319&with_genres=35&language=pt-BR").asJson();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(httpResponse.getBody().toString());
            String prettyJsonString = gson.toJson(je);
            
            response.setCharacterEncoding("UTF8");
            response.getWriter().println(prettyJsonString);
            
        } catch (UnirestException ex) {
            Logger.getLogger(comedy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
