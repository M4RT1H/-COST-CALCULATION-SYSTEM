package costcalcapp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private static ServerSocket serverSocket;
    private Socket clientAccepted;
    public static ObjectInputStream sois;
    public static ObjectOutputStream soos;

    private static int numOfClients;

    public static void main(String[] arg) {
        sois = null;//объявление байтового потока ввода
        soos = null;//объявление байтового потока вывода
        serverSocket = null;
        //clientAccepted = null;//объявление  объекта класса Socket
        try {
            //numOfClients = 0; // счётчик подключений
            ServerWorker.maxClients = 10;

            System.out.println("server starting....");
            serverSocket = new ServerSocket(12525);//создание сокета сервера
            System.out.println("server is started");

            for (int i = 0; i < arg.length; i++) {
                if (arg[i].equals("max")) {
                    ServerWorker.maxClients = Integer.parseInt(arg[i + 1]);
                }

                if (arg[i].equals("logging")) {
                    System.out.println("logging is on");
                    ServerWorker.logging = true;
                }
            }

            System.out.println("maxClients: " + ServerWorker.maxClients);

            while (true) {
                // ждём нового подключения, после чего запускаем обработку клиента
                // в новый вычислительный поток и увеличиваем счётчик на единичку

                new Server(serverSocket.accept());
            }

        } catch (Exception e) {
            System.out.println("init error: " + e);
        } // вывод исключений
        finally {
            try {
                //clientAccepted.close();//закрытие сокета, выделенного для клиента
                serverSocket.close();//закрытие сокета сервера
            } catch (Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }
    }

    public Server(Socket s) {
        // копируем данные
        this.clientAccepted = s;

        numOfClients++;
        System.out.println("client '" + numOfClients + "' is joined");

        // и запускаем новый вычислительный поток run()
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run() {
        try {
            // из сокета клиента берём поток входящих данных
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            // и оттуда же - поток данных от сервера к клиенту
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            ServerWorker serverWorker = new ServerWorker(sois, soos, numOfClients);
            serverWorker.runServerWorker();
            System.out.println("client '" + numOfClients + "' disconnected");
            numOfClients--;
        } catch (Exception e) {
            System.out.println("init error: " + e);
        } // вывод исключений
    }

}
