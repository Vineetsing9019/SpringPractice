// Learning entity models in a Spring Boot e-commerce project - practice class from ShopKart, pushed 2026-10-19

package com.itvedant.shopkart.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String mobile;
    
    @OneToMany(mappedBy = "user")
    private List<Cart> myCart;
    
    public List<Cart> getMyCart(){
    	return myCart;
    }
    public void setMyCart(List<Cart>)
    this.myCart = myCart;

    @ElementCollection
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // DB login code, for userDetails
    @Transient // no column will be created for  authorities
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // constructors - to set up the UserDetails object with
    public User() {}

    public User(int id, String username, String email, String password,
            String mobile, List<String> roles,
            Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.roles = roles;
        this.authorities = authorities;
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


