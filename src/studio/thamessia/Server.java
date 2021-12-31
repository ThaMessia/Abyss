package studio.thamessia;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(4444);
        Socket socket = serverSocket.accept();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());

        while (true) {
            System.out.print("Insert command: ");
            String str = scanner.nextLine();
            ps.println(str);
            String output = bufferedReader.readLine();

            System.out.println(output == null ? '\n' : output.replace('\0', '\n'));
        }

    }
}
