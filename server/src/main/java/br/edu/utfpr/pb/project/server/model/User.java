package br.edu.utfpr.pb.project.server.model;

import br.edu.utfpr.pb.project.server.annotation.UniqueCpf;
import br.edu.utfpr.pb.project.server.annotation.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    @NotEmpty
    private String name;

    @UniqueEmail
    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.email.NotNull}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.password.NotNull}")
    @Size(min = 6, message = "{br.edu.utfpr.pb.project.server.user.password.Size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{br.edu.utfpr.pb.project.server.user.password.Pattern}")
    private String password;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.birthDate.NotNull}")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.gender.NotNull}")
    @NotEmpty
    private String gender;

    @UniqueCpf
    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.password.NotNull}")
    @Size(max = 14)
    @NotEmpty
    private String cpf;

    @NotNull(message = "{br.edu.utfpr.pb.project.server.user.phone.NotNull}")
    @NotEmpty
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<Address> addresses;

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public String getUsername() {
        return email;
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
