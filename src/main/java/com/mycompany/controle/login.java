/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 //https://software.dzhuvinov.com/cors-filter-installation.html

package com.mycompany.controle;

import com.google.gson.Gson;
import controles.UsuarioController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Application;
import modelos.Erro;
import modelos.Usuario;

/**
 *
 * @author gutol
 */
public class login extends HttpServlet {

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
        Usuario user = gson.fromJson(request.getReader(), Usuario.class);
                
        UsuarioController ucontrol = new UsuarioController();
                
        if(ucontrol.login(user)){
            //sinalizo que funcionou
            request.getSession().setAttribute("usuario", user);
            //colocando o id da seção num local de acesso global
            getServletContext().setAttribute(request.getSession().getId(), request.getSession());
            //preparando a resposa que levará o id da seção para o react do outro lado
            user.setSessionID(request.getSession().getId());
            
            String json = gson.toJson(user);
            
            response.getWriter().println(json);
        }else{        
            //sinalizo que deu ruim
            request.getSession().removeAttribute("usuario");
            
            Erro erro = new Erro();
            erro.setDescricao("Login Não Realizado!");
            erro.setCodigo("001");            
      
            String json = gson.toJson(erro);            
            response.getWriter().println(json);
        }               
    }
    
    

}
