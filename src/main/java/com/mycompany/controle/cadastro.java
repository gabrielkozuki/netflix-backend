
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

public class cadastro extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Converte uma string em um objeto no formato indicado
        Gson gson = new Gson();
        Usuario user = gson.fromJson(request.getReader(), Usuario.class);
                
        UsuarioController ucontrol = new UsuarioController();
                
        if(ucontrol.cadastro(user) != null){
            response.getWriter().println("200");
        }else{
            Erro erro = new Erro();
            erro.setDescricao("Erro no cadastro");
            erro.setCodigo("001");            
            
            String json = gson.toJson(erro);            
            response.getWriter().println(json);
        }               
    }
}
