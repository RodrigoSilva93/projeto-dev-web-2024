package br.edu.utfpr.pb.project.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.name.NotNull}")
    private String name;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.email.NotNull}")
    private String email;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.password.NotNull}")
    @Size(min = 6, message = "{br.edu.utfpr.pb.project.server.user.password.Size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{br.edu.utfpr.pb.project.server.user.password.Pattern}")
    private String password;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.birthDate.NotNull}")
    @Pattern(regexp = "\\d+", message = "{br.edu.utfpr.pb.project.server.user.birthDate.Pattern}")
    private Date birthDate;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.gender.NotNull}")
    private String gender;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.password.NotNull}")
    @Size(max = 14)
    private String cpf;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.phone.NotNull}")
    private String phone;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.cep.NotNull}")
    private String cep;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.country.NotNull}")
    private String country;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.state.NotNull}")
    private String state;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.city.NotNull}")
    private String city;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.district.NotNull}")
    private String district;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.street.NotNull}")
    private String street;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.number.NotNull}")
    private Integer number;

    private String reference;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    //TODO verificar, estava retornando erro sem este override
    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
