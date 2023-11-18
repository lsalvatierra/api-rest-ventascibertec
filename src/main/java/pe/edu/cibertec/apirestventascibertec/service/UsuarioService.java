package pe.edu.cibertec.apirestventascibertec.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.apirestventascibertec.model.bd.Rol;
import pe.edu.cibertec.apirestventascibertec.model.bd.Usuario;
import pe.edu.cibertec.apirestventascibertec.model.bd.sp.RolUsuarioSp;
import pe.edu.cibertec.apirestventascibertec.model.bd.sp.UsuarioSp;
import pe.edu.cibertec.apirestventascibertec.repository.RolUsuarioSpRepository;
import pe.edu.cibertec.apirestventascibertec.repository.UsuarioSpRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {

    private RolUsuarioSpRepository rolUsuarioRepository;

    private UsuarioSpRepository usuarioRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder=
            new BCryptPasswordEncoder();

    public Optional<UsuarioSp> autenticarUsuario(String usuario,
                                               String password){
        Optional<UsuarioSp> objUsuario =
                usuarioRepository.autenticarUsuario(usuario,password);
        if(objUsuario.isEmpty()){
            return Optional.empty();
        }
        return objUsuario;
    }

    public String[] listarRolesPorUsuario(int idusuario){
        List<RolUsuarioSp> rolUsuarioList =
                rolUsuarioRepository.listarRolesPorUsuario(idusuario);
        String[] lisRoles = new String[rolUsuarioList.size()];
        for (int i =0; i < rolUsuarioList.size(); i++){
            lisRoles[i] = rolUsuarioList.get(i).getNomrol();
        }
        return lisRoles;
    }
}
