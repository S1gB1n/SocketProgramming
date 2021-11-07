import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;

        /*
            create welcoming socket at port 6789
            - listening for incoming request, not server for talking.
            - OS binding of port: 6789
            - it can also do IP address (find an example)
            - returns a Socket Object
         */
		ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("Server listening on port: 6789");

		while (true) {

            /*
                wait, on welcoming socket accept() method for client contact create,
                new socket on return
             */
			Socket connectionSocket = welcomeSocket.accept();
            
            /*
                create input strea, attached to socket 
             */
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			
            /*
                create output stream, attached to socket
             */
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			System.out.println("Accepted TCP connection from" 
					+ connectionSocket.getInetAddress() 
					+ ":" + connectionSocket.getPort());
			try {
				while (true) {
                    /* read in line from socket */
					clientSentence = inFromClient.readLine();

					capitalizedSentence = clientSentence.toUpperCase() + '\n';

                    /* write out line to socket */
					outToClient.writeBytes(capitalizedSentence);
				}
			} catch (Exception e) {
				// TODO: handle exception, if client closed connection, print:
				System.out.println("Client closed connection.");
			}
		}
	}
}