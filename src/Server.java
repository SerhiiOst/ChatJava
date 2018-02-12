import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int PORT = 8000;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        try {
            while (true){
                new Handler(serverSocket.accept()).start();
            }
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    private static class Handler extends Thread{
        String name;
        Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket){
            this.socket = socket;
        }

        public void run(){
            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("OK");

                while (true){
                    String line = in.readLine();
                    System.out.println(line);
                    out.println(line);
                    break;
                }
            }catch (Exception ex){

            }
        }
    }
}
