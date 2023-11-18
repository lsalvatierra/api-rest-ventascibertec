package pe.edu.cibertec.apirestventascibertec.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.apirestventascibertec.model.bd.Usuario;
import pe.edu.cibertec.apirestventascibertec.repository.UsuarioRepository;


@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public Usuario findByNomusuario(String usuario){
        return usuarioRepository.findByNomusuario(usuario);
    }


}
