
package com.mycompany.controle;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controles.TituloController;
import javax.servlet.http.HttpSession;
import modelos.Erro;
import patterns.ErroBuilder;
import patterns.GsonSingleton;

public class romances extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            TituloController tcontrol = new TituloController();
            HttpSession session = request.getSession();
            
            String json = tcontrol.getTitulos(session, TituloController.TituloRota.ROMANCES);
            
            response.setCharacterEncoding("UTF8");
            response.getWriter().println(json);
            
        } catch (Exception ex) {
            Erro erro = new ErroBuilder()
                .descricao(ex.getMessage())
                .codigo("001")
                .build();
            
            Gson gson = GsonSingleton.getInstance();
            String json = gson.toJson(erro);
            response.getWriter().println(json);
        }
        
    }
}
