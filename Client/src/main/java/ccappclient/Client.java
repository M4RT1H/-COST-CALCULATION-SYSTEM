package ccappclient;

import ccappclient.view.MenuAdmin;
import ccappclient.view.UserRegForm;
import ccappclient.view.MenuUser;
import ccappclient.view.Users.EditUser;
import ccappclient.view.ErrorWnd;
import ccappclient.view.EnterForm;
import ccappclient.view.CompRegForm;
import ccappclient.view.Users.UserRights;
import ccappclient.view.calculating.InsertCalculatingTable;
import ccappclient.view.products.InsertProductsTable;
import ccappclient.view.materials.InsertMaterialsTable;
import ccappclient.view.company.InsertCompanyTable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ccappclient.view.calculating.TimeSeriesChart;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JFrame;

public class Client {

    public static boolean logging;

    public static Socket clientSocket;
    public static ObjectOutputStream coos;
    public static ObjectInputStream cois;
    public static JFrame jf;
    public static JFrame errorframe;
    public static JFrame user_;

    private static String serverMessage;
    public static Map<String, String> stringmap;

    public static void main(String[] arg) {
        try {

            for (int i = 0; i < arg.length; i++) {
                if (arg[i].equals("logging")) {
                    System.out.println("logging is on");
                    logging = true;
                }
            }

            System.out.println("Соединение с сервером....");
            clientSocket = new Socket("127.0.0.1", 12525);//установление 
//соединения между локальной машиной и указанным портом узла
            System.out.println("Соединение установлено....");

            coos = new ObjectOutputStream(clientSocket.getOutputStream());//поток вывода
            cois = new ObjectInputStream(clientSocket.getInputStream());//поток ввода

            sendMessage("_can_start");
            serverMessage = receiveMessage();
            if (!serverMessage.equals("_allowed_to_start")) {
                System.out.println("Cant start! Server message:" + serverMessage);
                return;
            }

            do {
                serverMessage = receiveMessage();			//принимаем серверную команду

                if (serverMessage.equals("_insert_calculating_table")) {

                    jf = new InsertCalculatingTable();
                    jf.setVisible(true);
                    do {
                        serverMessage = receiveMessage();
                        if (serverMessage.equals("_chart_wnd")) {
                            String productID = receiveMessage();
                            String wndName = "График изменения стоимости изделия";
                            TimeSeriesChart timeSeriesChart = new TimeSeriesChart(wndName, productID);
                            //jf = timeSeriesChart;
                            timeSeriesChart.setVisible(true);
                            //jf.setVisible(true);
                            continue;
                        }
                        if (serverMessage.equals("_update_components")) {
                            //jf.getContentPane().removeAll();
                            jf.setVisible(false);
                            jf = new InsertCalculatingTable();
                            jf.setVisible(true);
                            continue;
                        }
                        if (serverMessage.equals("_close_wnd")) {
                            jf.setVisible(false);
                            break;
                        }
                    } while (true);
                    continue;
                }
                if (serverMessage.equals("_insert_products_table")) {

                    jf = new InsertProductsTable();
                    jf.setVisible(true);
                    do {
                        serverMessage = receiveMessage();
                        if (serverMessage.equals("_update_components")) {
                            //jf.getContentPane().removeAll();
                            jf.setVisible(false);
                            jf = new InsertProductsTable();
                            jf.setVisible(true);
                            continue;
                        }
                        if (serverMessage.equals("_close_wnd")) {
                            jf.setVisible(false);
                            break;
                        }
                    } while (true);
                    continue;
                }
                if (serverMessage.equals("_insert_materials_table")) {

                    jf = new InsertMaterialsTable();
                    jf.setVisible(true);
                    do {
                        serverMessage = receiveMessage();
                        if (serverMessage.equals("_update_components")) {
                            //jf.getContentPane().removeAll();
                            jf.setVisible(false);
                            jf = new InsertMaterialsTable();
                            jf.setVisible(true);
                            continue;
                        }
                        if (serverMessage.equals("_close_wnd")) {
                            jf.setVisible(false);
                            break;
                        }
                    } while (true);
                    continue;
                }

                if (serverMessage.equals("_insert_company_table")) {

                    jf = new InsertCompanyTable();
                    jf.setVisible(true);
                    do {
                        serverMessage = receiveMessage();
                        if (serverMessage.equals("_update_components")) {
                            //jf.getContentPane().removeAll();
                            jf.setVisible(false);
                            jf = new InsertCompanyTable();
                            jf.setVisible(true);
                            continue;
                        }
                        if (serverMessage.equals("_close_wnd")) {
                            jf.setVisible(false);
                            break;
                        }
                    } while (true);
                    continue;
                }
                if (serverMessage.equals("_insert_users")) {

                    jf = new EditUser();
                    jf.setVisible(true);

                    do {
                        serverMessage = receiveMessage();
                        if (serverMessage.equals("_update_components")) {

                            //jf.getContentPane().removeAll();
                            jf.setVisible(false);
                            jf = new EditUser();
                            jf.setVisible(true);
                            continue;
                        }

                        if (serverMessage.equals("_error_wnd")) {
                            errorframe = new ErrorWnd();
                            errorframe.setVisible(true);
                            errorframe.setAlwaysOnTop(true);
                            continue;
                        }

                        if (serverMessage.equals("_rights_edit")) {

                            user_ = new UserRights();
                            user_.setVisible(true);
                            continue;
                        }
                        if (serverMessage.equals("_close_wnd")) {

                            jf.setVisible(false);
                            break;
                        }
                    } while (true);
                    continue;
                }
//                if (serverMessage.equals("_rights_edit")) {
//                    jf = new UserRights();
//                    jf.setVisible(true);
//                    continue;
//                }
                if (serverMessage.equals("_company_reg")) {
                    jf = new CompRegForm();
                    jf.setVisible(true);
                    continue;
                }
//                if (serverMessage.equals("_edit_users")) {
//                    jf = new EditUser();
//                    jf.setVisible(true);
//                    continue;
//                }
                if (serverMessage.equals("_user_reg")) {
                    jf = new UserRegForm();
                    jf.setVisible(true);
                    continue;
                }

                if (serverMessage.equals("_enter_form")) {
                    jf = new EnterForm();
                    jf.setVisible(true);
                    continue;
                }

                if (serverMessage.equals("_error_wnd")) {
                    errorframe = new ErrorWnd();
                    errorframe.setVisible(true);
                    continue;
                }

                if (serverMessage.equals("_close_wnd")) {
                    jf.setVisible(false);
                    continue;
                }

                if (serverMessage.equals("_menu_admin")) {

                    jf = new MenuAdmin();
                    jf.setVisible(true);
                    continue;
                }

                if (serverMessage.equals("_menu_user")) {
                    jf = new MenuUser();
                    jf.setVisible(true);
                    continue;
                }

                if (serverMessage.equals("_exit")) {
                    jf.setVisible(false);
                    return;
                } else {
                    System.out.println("получена неизвестная команда!");
                }

            } while (true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                coos.close();//закрытие потока вывода 
                cois.close();//закрытие потока ввода
                clientSocket.close();//закрытие сокета
                exit(0);
            } catch (Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }
    }

    public static void sendMessage(String msg) {
        try {
            coos.writeObject(msg);

            if (logging) {
                System.out.println("message sended: " + msg);
            }
        } catch (IOException e) {
            System.out.println("sendObject = " + e);
        }
    }

    public static void sendObject(Object obj) {
        try {
            coos.writeObject(obj);

            if (logging) {
                System.out.println("object sended!");
            }
        } catch (IOException e) {
            System.out.println("sendObject = " + e);
        }
    }

    public static String receiveMessage() {
        String msg = new String("-no message-");
        try {
            msg = (String) cois.readObject();

            if (logging) {
                System.out.println("~server~: " + msg);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("receiveMessage = " + e);
        }
        return msg;
    }

    public static ArrayList<String[]> receiveResultSet() {
        ArrayList<String[]> rs = null;
        try {
            rs = (ArrayList<String[]>) cois.readObject();

            if (logging) {
                System.out.println("resultSets received ");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("receiveResultSet = " + e);
        }
        return rs;
    }

}
