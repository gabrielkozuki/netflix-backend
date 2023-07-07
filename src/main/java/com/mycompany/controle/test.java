
package com.mycompany.controle;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Erro;
import modelos.Usuario;
import patterns.ErroBuilder;
import patterns.GsonSingleton;

public class test extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Gson gson = GsonSingleton.getInstance();
        Usuario usuario = gson.fromJson(request.getReader(), Usuario.class);
        
        System.out.println("Teste: ");
        System.out.println(request.getReader());
        System.out.println(usuario.getSessionID());
        
        //HttpSession session = request.getSession();  //não encontra
        HttpSession session = (HttpSession) getServletContext().getAttribute(usuario.getSessionID());
        
        if(session != null && session.getAttribute("usuario") != null){
            usuario =  (Usuario) session.getAttribute("usuario");
            String json = gson.toJson(usuario);
            
            response.getWriter().println(json);
            
        } else {
            Erro erro = new ErroBuilder()
                .descricao("Login Não Realizado!")
                .codigo("001")
                .build();
            
            String json = gson.toJson(erro);     
            response.getWriter().println(json);

        }
            
    }

}
