
package com.mycompany.controle;

import com.google.gson.Gson;
import controles.UsuarioController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Erro;
import modelos.Usuario;
import patterns.ErroBuilder;
import patterns.GsonSingleton;

public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //COnverte uma string em um objeto no formato indicado
        Gson gson = GsonSingleton.getInstance();
        Usuario user = gson.fromJson(request.getReader(), Usuario.class);
                
        UsuarioController ucontrol = new UsuarioController();
                
        if(ucontrol.login(user)) {
            request.getSession().setAttribute("usuario", user);
            //colocando o id da seção num local de acesso global
            getServletContext().setAttribute(request.getSession().getId(), request.getSession());
            user.setSessionID(request.getSession().getId());
            
            String json = gson.toJson(user);
            
            response.getWriter().println(json);
        }else{
            request.getSession().removeAttribute("usuario");
            
            Erro erro = new ErroBuilder()
                .descricao("Login Não Realizado!")
                .codigo("001")
                .build();
      
            String json = gson.toJson(erro);            
            response.getWriter().println(json);
        }               
    }
    
    

}
