/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import java.util.ArrayList;
import modelos.Usuario;

/**
 *
 * @author gutol
 */
public class UsuarioController {
     public static ArrayList<Usuario> usuarios = createUsers();
     
    public UsuarioController(){}
    
    public static ArrayList<Usuario> createUsers(){
        ArrayList<Usuario> users = new ArrayList();
        
        Usuario u1 = new Usuario();
        u1.setNome("Arthur da Silva Sauro");
        u1.setEmail("arthur@teste.com");
        u1.setSenha("1234");
        
        users.add(u1);


        Usuario u2 = new Usuario();
        u2.setNome("João da Silva Sauro");
        u2.setEmail("joao@teste.com");
        u2.setSenha("1234");
        
        users.add(u2);
        
        return users;        
    }
    
    public boolean login(Usuario user){
        
        boolean logado = false;
        
        for(Usuario u: UsuarioController.usuarios){
            
            if(u.getEmail().equals(user.getEmail()) && u.getSenha().equals(user.getSenha())){
                logado = true;
                
                //copiar os dados do meu banco de dados para o objeto de retorno do login
                user.setNome(u.getNome());
                
                break;
            }
            
        }
        
        return logado;
    }
    
}
