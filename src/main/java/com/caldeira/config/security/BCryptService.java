package com.caldeira.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

public class BCryptService {

    // Instancia o encoder do BCrypt
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String criptografarSenha(String senhaPura) {
        return encoder.encode(senhaPura);
    }

    public static boolean verificarSenha(String senhaPura, String hashSalvo) {
        return encoder.matches(senhaPura, hashSalvo);
    }

    public static void main(String[] args) {
        String senhaExemplo = "minhaSenhaSecreta";

        // Criptografia
        String hashGerado = criptografarSenha(senhaExemplo);
        System.out.println("Hash: " + hashGerado);

        // Verificação
        boolean isValida = verificarSenha(senhaExemplo, hashGerado);
        System.out.println("A senha é válida? " + isValida);
    }
}