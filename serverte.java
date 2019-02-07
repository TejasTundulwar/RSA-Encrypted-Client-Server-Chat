
import java.io.*;
 import java.net.*;
import java.util.*;
import java.math.BigInteger;
import java.security.SecureRandom;






 public class serverte
 { 
public static void main(String[] args) throws Exception 
{ 




  /** Create an instance that can both encrypt and decrypt. */
  public RSA(int bits) {
    bitlen = bits;
    SecureRandom r = new SecureRandom();
    BigInteger p = new BigInteger(bitlen / 2, 100, r);
    BigInteger q = new BigInteger(bitlen / 2, 100, r);
    n = p.multiply(q);
    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
        .subtract(BigInteger.ONE));
    e = new BigInteger("3");
    while (m.gcd(e).intValue() > 1) {
      e = e.add(new BigInteger("2"));
    }
    d = e.modInverse(m);
  }

  /** Encrypt the given plaintext message. */
  public synchronized String encrypt(String message) {
    return (new BigInteger(message.getBytes())).modPow(e, n).toString();
  }

  /** Encrypt the given plaintext message. */
  public synchronized BigInteger encrypt(BigInteger message) {
    return message.modPow(e, n);
  }

  /** Decrypt the given ciphertext message. */
  public synchronized String decrypt(String message) {
    return new String((new BigInteger(message)).modPow(d, n).toByteArray());
  }

  /** Decrypt the given ciphertext message. */
  public synchronized BigInteger decrypt(BigInteger message) {
    return message.modPow(d, n);
  }

  /** Generate a new public and private key set. */
  public synchronized void generateKeys() {
    SecureRandom r = new SecureRandom();
    BigInteger p = new BigInteger(bitlen / 2, 100, r);
    BigInteger q = new BigInteger(bitlen / 2, 100, r);
    n = p.multiply(q);
    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
        .subtract(BigInteger.ONE));
    e = new BigInteger("3");
    while (m.gcd(e).intValue() > 1) {
      e = e.add(new BigInteger("2"));
    }
    d = e.modInverse(m);
  }

  /** Return the modulus. */
  public synchronized BigInteger getN() {
    return n;
  }

  /** Return the public key. */
  public synchronized BigInteger getE() {
    return e;
  }


ServerSocket sersock = new ServerSocket(3000);
 System.out.println("Server ready for chatting"); 
Socket sock = sersock.accept( ); // reading from keyboard 
 BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in)); // sending to client 
 OutputStream ostream = sock.getOutputStream();
 PrintWriter pwrite = new PrintWriter(ostream, true);   // receiving from server 
 InputStream istream = sock.getInputStream(); 
BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));  
 String receiveMessage, sendMessage; 
while((receiveMessage = receiveRead.readLine()) == null)
 { 
if((receiveMessage = receiveRead.readLine()) != null)
 { plaintext = rsa.decrypt(receiveMessage);

    String text2 = new String(plaintext.toByteArray());
 System.out.println("PlainText:"+text2);
 } 
RSA rsa = new RSA(1024);
        String text1;
    Scanner s1= new Scanner(System.in);
    text1=s1.nextLine();
    System.out.println("Plaintext: " + text1);
    BigInteger plaintext = new BigInteger(text1.getBytes());

    BigInteger ciphertext = rsa.encrypt(plaintext);
sendMessage = ciphertext;
 pwrite.println(sendMessage); 
pwrite.flush();
 } } }}
