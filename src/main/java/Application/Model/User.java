package Application.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Entity
@Table(name="user")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    public User()
    {

    }

    public User(String role, String username, String password)
    {
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public User(String name, String address, String phone, String email, String username, String password, String locatie)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role="ROLE_USER";
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isAccountNonExpired()
    {
        return true;
    }

    public boolean isAccountNonLocked()
    {
        return true;
    }

    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    public boolean isEnabled()
    {
        return true;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return AuthorityUtils.createAuthorityList(this.getRole());
    }

}