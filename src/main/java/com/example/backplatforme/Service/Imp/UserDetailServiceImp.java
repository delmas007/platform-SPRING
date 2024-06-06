package com.example.backplatforme.Service.Imp;



import com.example.backplatforme.dto.EtudiantDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    public UserDetailServiceImp(@Lazy EtudiantServiceImpl utilisateurServiceImp) {
        this.utilisateurServiceImp = utilisateurServiceImp;
    }

    EtudiantServiceImpl utilisateurServiceImp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EtudiantDto utilisateurDto = utilisateurServiceImp.loadUserByUsername(username);
        if(utilisateurDto==null) throw new UsernameNotFoundException("pas D'utilisateur trouver");
        UserDetails userDetails = User
                .withUsername(utilisateurDto.getId())
                .password(utilisateurDto.getPassword())
                .build();
        return userDetails;
    }
}
