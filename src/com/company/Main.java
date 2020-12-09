package com.company;

import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyStore;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Ejercicio1();
        Ejercicio2();

/*      Path path = Paths.get("/home/oscar/Escritorio/textamagat");
        byte[] txtByte = Files.readAllBytes(path);

        File f = new File("/home/oscar/Escritorio/contraseñas.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br =  new BufferedReader(fr);
        int keysize = 128;
        byte[] decryptedTxt = null;
        while (decryptedTxt == null) {
            String line = br.readLine();
            SecretKey secretKey = Cifrar.passwordKeyGeneration(line, keysize);
            decryptedTxt = Cifrar.decryptData(secretKey, txtByte);
        }

        System.out.println("Texto desencriptado: " + new String(decryptedTxt));
*/

    }

    private static void Ejercicio1() {
        Scanner scanner = new Scanner(System.in);

        int keySize = 1024;
        Cifrar.randomGenerate(keySize);

        KeyPair keyPair = Cifrar.randomGenerate(keySize);
        keyPair.getPrivate();
        keyPair.getPublic();

        System.out.println("Escribe para poder encriptar: ");
        String txt = scanner.nextLine();
        System.out.println("\n");

        byte[] textEncrypted = Cifrar.encryptData((SecretKey) keyPair.getPrivate(), txt.getBytes());

        byte[] textDecrypted = Cifrar.decryptData((SecretKey) keyPair.getPrivate(), textEncrypted);

        System.out.println("Text Encrypted: " + new String(textEncrypted));
        System.out.println("Text Decrypted: " + new String(textDecrypted));

    }

    private static void Ejercicio2() throws Exception {
        Scanner sc = new Scanner(System.in);
        KeyStore loadKeyStore = Cifrar.loadKeyStore("/home/oscar/keystore_oscar.ks", "123456789");
        System.out.println("Tipos de Keystore que hay: " + loadKeyStore.getType());
        System.out.println("Medida del almacenamiento que se usa: " + loadKeyStore.size());
        Enumeration enumrtion = loadKeyStore.aliases();

        while (enumrtion.hasMoreElements()) {
            System.out.println("Alias: " + enumrtion.nextElement());
        }

        System.out.print("Que alias deseas? ");
        String alias = sc.next();
        System.out.println("Certificado: " + loadKeyStore.getCertificate(alias));


        char[] psswd = "123456789".toCharArray();
        SecretKey secretKey = Cifrar.keygenKeyGeneration("scKey", 128);
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
        KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(psswd);
        loadKeyStore.setEntry("mykey123", secretKeyEntry, protectionParameter);
        loadKeyStore.store(new FileOutputStream("/home/oscar/keystore_oscar.ks"), "123456789".toCharArray());
    }

    private static void Ejercicio3() {

    }
}
