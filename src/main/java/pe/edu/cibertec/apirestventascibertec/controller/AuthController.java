package pe.edu.cibertec.apirestventascibertec.controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.apirestventascibertec.exception.BadCredentialsException;
import pe.edu.cibertec.apirestventascibertec.exception.ResourceNotFoundException;
import pe.edu.cibertec.apirestventascibertec.model.bd.Usuario;
import pe.edu.cibertec.apirestventascibertec.model.bd.sp.UsuarioSp;
import pe.edu.cibertec.apirestventascibertec.model.response.UsuarioResponse;
import pe.edu.cibertec.apirestventascibertec.service.DetalleUsuarioService;
import pe.edu.cibertec.apirestventascibertec.service.UsuarioService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {


    private DetalleUsuarioService detalleUsuarioService;

    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioResponse> autenticarUsuario(
            @RequestParam("usuario") String usuario,
            @RequestParam("password") String password
    ) throws Exception {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuario,
                            password));
            if(authentication.isAuthenticated()){
                Usuario objUsuario = detalleUsuarioService.findByNomusuario(usuario);
                String token = generarToken(objUsuario);
                UsuarioResponse usuarioResponse =
                        new UsuarioResponse(objUsuario.getIdusuario(),
                                usuario,
                                token);
                return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception ex){
            throw new Exception("Usuario y/o password incorrecto.");
        }

        /*UsuarioSp objUsuario = usuarioService
                .autenticarUsuario(usuario, password)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario o password incorrecto."));
        String token = generarToken(usuario,
                Integer.parseInt(objUsuario.getIdusuario().toString()));
        UsuarioResponse usuarioResponse =
                new UsuarioResponse(objUsuario.getIdusuario(),
                        objUsuario.getNomusuario(),
                        token);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);*/
    }


    private String generarToken(Usuario objUsuario){
        String clave = "@Cibertec2023"; // dinamico desde la BD
        List<GrantedAuthority> grantedAuthorityList =
                detalleUsuarioService.obtenerListaRolesUsuario(objUsuario.getRoles());
        String token = Jwts
                .builder()
                .setId(objUsuario.getIdusuario().toString())
                .setSubject(objUsuario.getNomusuario())
                .claim("authorities",
                        grantedAuthorityList.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 300000))
                .signWith(SignatureAlgorithm.HS512, clave.getBytes())
                .compact();
        return token;
    }


}
