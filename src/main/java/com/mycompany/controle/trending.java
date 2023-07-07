
package com.mycompany.controle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controles.TituloController;
import javax.servlet.http.HttpSession;
import modelos.Erro;

public class trending extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            TituloController tcontrol = new TituloController();
            HttpSession session = request.getSession();
            
            String json = tcontrol.getTitulos(session, TituloController.TituloRota.TRENDING);
            
            response.setCharacterEncoding("UTF8");
            response.getWriter().println(json);
            
        } catch (Exception ex) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Erro erro = new Erro();
            erro.setDescricao(ex.getMessage());
            erro.setCodigo("001");
            
            String json = gson.toJson(erro);
            response.getWriter().println(json);
        }
        
    }
}
