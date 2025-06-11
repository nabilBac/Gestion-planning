package com.securite.planning;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Importe la classe pour hacher les mots de passe

public class GenererMotDePasseAdmin { // Nom de la classe
    public static void main(String[] args) { // Méthode principale qui sera exécutée
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // Crée un encodeur de mot de passe BCrypt

        // >>>>>> DÉFINISSEZ VOTRE NOUVEAU MOT DE PASSE CLAIR ICI <<<<<<
        // C'est le mot de passe que VOUS allez utiliser pour vous connecter.
        String motDePasseAdminClair = "admin123"; // <<< REMPLACEZ "MonSuperPassAdmin" par le mot de passe de votre choix (ex: "admin123", "password")

        // Hache le mot de passe clair que vous avez choisi
        String hashGenere = encoder.encode(motDePasseAdminClair);

        // Affiche les informations importantes dans la console d'IntelliJ
        System.out.println("------------------------------------------------------------------");
        System.out.println("NOM D'UTILISATEUR POUR SE CONNECTER : Admin"); // C'est le nom d'utilisateur par défaut de votre compte admin en BDD
        System.out.println("MOT DE PASSE CLAIR (à utiliser pour se connecter) : " + motDePasseAdminClair);
        System.out.println("HASH À METTRE DANS LA BASE DE DONNÉES (pour l'utilisateur admin) : " + hashGenere);
        System.out.println("------------------------------------------------------------------");
    }
}