/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 //https://software.dzhuvinov.com/cors-filter-installation.html

package com.mycompany.controle;

import com.google.gson.Gson;
import controles.UsuarioController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Application;
import modelos.Erro;
import modelos.Titulo;

/**
 *
 * @author gutol
 */
public class trending extends HttpServlet {

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
        
        //COnverte uma string em um objeto no formato indicado
        Gson gson = new Gson();                       
        Titulo titulo = gson.fromJson(request.getReader(), Titulo.class);
                
        TituloController tcontrol = new TituloController();
        Titulo <> titulos = tcontrol.getTrending();
            
        String json = gson.toJson(titulos);
        response.getWriter().println(json);           
    }
}
