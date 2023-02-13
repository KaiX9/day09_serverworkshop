package sg.edu.nus.iss.app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class App 
{
    public static void main( String[] args ) throws IOException {
        
        Random random = new Random();

        // generate random number between 0 to 99
        Integer randomNumber = random.nextInt(100);

        // store my guess
        Integer myGuess = 0;

        // open the socket server to listen on port 1234 for input
        System.out.println("Server running on port 1234");
        ServerSocket ss = new ServerSocket(1234);
        Socket s = ss.accept();

        // prepare input coming through socket from client (receiving)
        InputStream is = s.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        // preparing sending data out through socket to client (sending out)
        OutputStream os = s.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        String msgReceived = "";

        while (!msgReceived.equals("quit")) {
            // guess XX 
            msgReceived = dis.readUTF();

            if (msgReceived.contains("guess")) {
                myGuess = Integer.parseInt(msgReceived.substring(6));
            }

            if (myGuess < randomNumber) {
                dos.writeUTF("Your guessed number is lower.");
            } else if (myGuess > randomNumber) {
                dos.writeUTF("Your guessed number is higher.");
            } else {
                dos.writeUTF("You have finally guessed it right!");
            }
            // ensure records are written and sent across the socket
            dos.flush();
        }
        // close the input and output streams
        dos.close();
        bos.close();
        os.close();

        dis.close();
        bis.close();
        is.close();
    }
}
