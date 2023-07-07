
package controles;

import java.util.ArrayList;
import modelos.Usuario;

public class UsuarioController {
    public static ArrayList<Usuario> usuarios = new ArrayList();
     
    public UsuarioController(){}
    
    public boolean login(Usuario user){
        boolean logado = false;
        
        for(Usuario u: UsuarioController.usuarios){
            
            if(u.getEmail().equals(user.getEmail()) && u.getSenha().equals(user.getSenha())){
                logado = true;
                
                //copiar os dados do meu banco de dados para o objeto de retorno do login
                user.setNome(u.getNome());
                user.setIdade(u.getIdade());
                break;
            }
            
        }
        
        return logado;
    }
    
    public Usuario cadastro(Usuario user){
        usuarios.add(user);
        return user;
    }
    
}
