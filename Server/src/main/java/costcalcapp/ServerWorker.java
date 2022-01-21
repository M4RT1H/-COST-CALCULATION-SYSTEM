/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package costcalcapp;

import costcalcapp.model.Employee;
import costcalcapp.model.Company;
import costcalcapp.model.Material;
import costcalcapp.model.Product;
import costcalcapp.model.User;
import costcalcapp.controller.DBWorker;
import costcalcapp.controller.InsertCalculatingTable;
import costcalcapp.controller.InsertCompanyTable;
import costcalcapp.controller.InsertMaterialsTable;
import costcalcapp.controller.InsertProductsTable;
import costcalcapp.controller.EditUsers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author M4RT1H
 */
public class ServerWorker {

    public ObjectInputStream sois;
    public ObjectOutputStream soos;
    public int numOfClient;
    public static int maxClients;
    public static boolean logging;

    private DBWorker worker;
    private Statement statement;
    private PreparedStatement preparedStmt;

    private String query;
    private ResultSet resultSet;

    private String clientMessageRecieved;
    public Map<String, String> stringmap;

    private Company company;
    private User user;
    private Employee employee;
    private Material material;
    private Product product;

    private String employeeNSn; //имя-фамилия авторизированного сотрудника
    private String employeeID;

    ServerWorker(ObjectInputStream sois, ObjectOutputStream soos, int numOfClient) {
        this.sois = sois;
        this.soos = soos;
        this.numOfClient = numOfClient;
    }

    public void serverMessage(String msg) {
        if (logging) {
            System.out.println("Worker for client" + numOfClient + ": " + msg);
        }
    }

    public void sendMessage(String msg) {
        try {
            soos.writeObject(msg);

            if (logging) {
                serverMessage("message sended: " + msg);
            }
        } catch (IOException e) {
            System.out.println("sendMessage = " + e);
        }
    }

    public void sendResultSet(ArrayList<String[]> resultSets) {
        try {
            soos.writeObject(resultSets);

            if (logging) {
                serverMessage("resultSets sended ");
            }
        } catch (IOException e) {
            System.out.println("sendMessage = " + e);
        }
    }

    public LinkedHashMap receiveStringMap() {
        LinkedHashMap strmap = new LinkedHashMap();
        try {
            strmap = (LinkedHashMap) sois.readObject();

            if (logging) {
                serverMessage("stringmap received");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("receiveStringMap = " + e);
        }

        return strmap;
    }

    public String receiveMessage() {
        String msg = new String();
        try {
            msg = (String) sois.readObject();

            if (logging) {
                serverMessage("~client~: " + msg);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("receiveMessage = " + e);
            return "_exception";
        }

        return msg;
    }

    public void runServerWorker() {
        try {

            worker = new DBWorker();
            statement = worker.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            do {
                clientMessageRecieved = receiveMessage();

                if (clientMessageRecieved.equals("_can_start")) {
                    if (numOfClient > maxClients) {
                        sendMessage("_too_many_connections");
                        return;
                    } else {
                        sendMessage("_allowed_to_start");
                    }
                }

                //начальная инициализация для работы 
                //(чтение данных о компании и вход пользователя)
                //чтение данных из БД о компании (если есть)
                query = "SELECT * FROM company";
                resultSet = statement.executeQuery(query);
                if (resultSet.last() == true) {
                    company = new Company();
                    company.resultSetToCompany(resultSet);
                    serverMessage("Company has been read");
                } else {
                    companyRegistration();
                    serverMessage("Company has been updated");
                }

                //проверка, есть ли хоть один администратор
                query = "SELECT * FROM users WHERE `UserRigths` = 'admin'";
                resultSet = statement.executeQuery(query);
                if (resultSet.last() == false) {
                    userRegistration("admin");
                }

                //вход пользователя
                sendMessage("_enter_form");
                stringmap = receiveStringMap();

                //регистрация пользователя
                if (stringmap.get("_registration") != null) {
                    userRegistration("user");
                }

                //поиск пользователя в БД и проверка пароля
                userEntering();

                //чтение из БД всех полей для пользователя
                query = "SELECT * FROM users WHERE `UserLogin` = '" + user.getUserLogin() + "'";
                resultSet = statement.executeQuery(query);
                if (resultSet.last() == true) {
                    user.resultSetToUser(resultSet);
                }

                //чтение данных о сотруднике, связанном с пользователем
                query = "SELECT * FROM employees WHERE `EmployeeID` = " + user.getEmployeeID();
                ResultSet employeeSet = statement.executeQuery(query);

                employee = new Employee();
                employee.resultSetToEmployee(employeeSet);
                if (employeeSet.last() == true) {
                    employeeNSn = employee.getEmployeeName() + " " + employee.getEmployeeSurname();
                    employeeID = Integer.toString(employee.getEmployeeID());
                } else {
                    employeeNSn = "Неизвестный сотрудник";
                }
                menu();

                //обработка команд, полученных от клиентской стороны
                do {
                    //получение команды клиента
                    clientMessageRecieved = receiveMessage();

                    if (clientMessageRecieved.equals("_calculating_button")) {
                        workWithCalculating();
                        continue;
                    }

                    if (clientMessageRecieved.equals("_company_button")) {
                        workWithCompany();
                        continue;
                    }

                    if (clientMessageRecieved.equals("_users_button")) {
                        workWithUsers();
                        continue;
                    }
//                    if (clientMessageRecieved.equals("_Edit_Rights_RadioButton")) {
//                        EditRights();
//                        continue;
//                    }

                    if (clientMessageRecieved.equals("_materials_button")) {
                        workWithMaterials();
                        continue;
                    }

                    if (clientMessageRecieved.equals("_products_button")) {
                        workWithProducts();
                        continue;
                    }

                    if (clientMessageRecieved.equals("_work_end")) {
                        sendMessage("_exit");
                        return;
                    } else {
                        break;
                    }

                } while (true);

            } while (true);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            serverMessage("init error: " + e);
        } // вывод исключений
        finally {
            try {
                sois.close();//закрытие потока ввода
                soos.close();//закрытие потока вывода
            } catch (Exception e) {
                e.printStackTrace();//вызывается метод исключения е
            }
        }
    }

    public void workWithCalculating() throws IOException, ClassNotFoundException {
        sendMessage("_close_wnd");
        sendMessage("_insert_calculating_table");
        InsertCalculatingTable ict = new InsertCalculatingTable(this);
        do {
            clientMessageRecieved = receiveMessage();

            if (clientMessageRecieved.equals("_price_chart")) {
                String msg = receiveMessage();
                sendMessage("_chart_wnd");
                sendMessage(msg);
                continue;
            }
            if (clientMessageRecieved.equals("_edit_button")) {
                ict.EditButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_delete_button")) {
                ict.DeleteButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_send_button")) {
                ict.SendButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_get_name_of_product")) {
                String msg = receiveMessage();
                ict.getNameOfProduct(msg);
                continue;
            }
            if (clientMessageRecieved.equals("_get_result_from_company")) {
                ict.getResultFromCompany();
                continue;
            }
            if (clientMessageRecieved.equals("_get_result_from_calculating")) {
                ict.getResultFromCalculating();
                continue;
            }
            if (clientMessageRecieved.equals("_get_result_from_calculating_for_id")) {
                String productID = receiveMessage();
                ict.getResultFromCalculating(productID);
                continue;
            }
            if (clientMessageRecieved.equals("_get_ids_from_products")) {
                ict.getIDsFromProducts();
                continue;
            }

            if (clientMessageRecieved.equals("_go_back")) {
                sendMessage("_close_wnd");
                menu();
                break;
            } else {
                break;
            }
        } while (true);
    }

    public void workWithMaterials() throws IOException, ClassNotFoundException {
        sendMessage("_close_wnd");
        sendMessage("_insert_materials_table");
        InsertMaterialsTable ict = new InsertMaterialsTable(this);
        do {
            clientMessageRecieved = receiveMessage();

            if (clientMessageRecieved.equals("_edit_button")) {
                ict.EditButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_delete_button")) {
                ict.DeleteButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_send_button")) {
                ict.SendButtonActionPerformed();
                // sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_get_result_from_materials")) {
                ict.getResultFromMaterials();
                continue;
            }
            if (clientMessageRecieved.equals("_go_back")) {
                sendMessage("_close_wnd");
                menu();
                break;
            } else {
                break;
            }
        } while (true);
    }

    public void workWithProducts() throws IOException, ClassNotFoundException {
        sendMessage("_close_wnd");
        sendMessage("_insert_products_table");
        InsertProductsTable ipt = new InsertProductsTable(this);
        do {
            clientMessageRecieved = receiveMessage();

            if (clientMessageRecieved.equals("_edit_button")) {
                ipt.EditButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_delete_button")) {
                ipt.DeleteButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_send_button")) {
                ipt.SendButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_send_button_matrates")) {
                ipt.SendButton1ActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_edit_button_matrates")) {
                ipt.EditButton1ActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_delete_button_matrates")) {
                ipt.DeleteButton1ActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_get_result_from_materials_rates")) {
                ipt.getResultFromMaterialsRates();
                continue;
            }
            if (clientMessageRecieved.equals("_get_result_from_products")) {
                ipt.getResultFromProducts();
                continue;
            }
            if (clientMessageRecieved.equals("_get_ids_from_materials")) {
                ipt.getIDsFromMaterials();
                continue;
            }
            if (clientMessageRecieved.equals("_go_back")) {
                sendMessage("_close_wnd");
                menu();
                break;
            } else {
                break;
            }
        } while (true);
    }

    public void EditRights() throws IOException, ClassNotFoundException {
        sendMessage("_close_wnd");
        sendMessage("_rights_edit");

        EditUsers us = new EditUsers(this);
        do {
            clientMessageRecieved = receiveMessage();
            if (clientMessageRecieved.equals("_Edit_Rights")) {
                us.EditUsersButton(employeeID);
                
                continue;
            }
            if (clientMessageRecieved.equals("_go_back")) {
                sendMessage("_close_wnd");
                menu();

                break;
            } else {
                break;
            }

        } while (true);

    }

    public void workWithUsers() throws IOException, ClassNotFoundException {

        sendMessage("_close_wnd");
        sendMessage("_insert_users");

        EditUsers us = new EditUsers(this);

        do {
            clientMessageRecieved = receiveMessage();
            
            if (clientMessageRecieved.equals("_delete_myself")) {
                us.DeleteButtonActionPerformed(employeeID);
                 //sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_Edit_Rights_RadioButton")) {
                us.EditUsersButton(employeeID);
                // sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_get_name_from_users")) {
                us.getNameFromUsers();
                continue;
            }
            if (clientMessageRecieved.equals("_go_back")) {
                sendMessage("_close_wnd");
                menu();

                break;
            } else {
                break;
            }
        } while (true);

    }

    public void workWithCompany() throws IOException, ClassNotFoundException {
        sendMessage("_close_wnd");
        sendMessage("_insert_company_table");
        InsertCompanyTable ict = new InsertCompanyTable(this);
        do {
            clientMessageRecieved = receiveMessage();

            if (clientMessageRecieved.equals("_edit_button")) {
                ict.EditButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_delete_button")) {
                ict.DeleteButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_send_button")) {
                ict.SendButtonActionPerformed();
                sendMessage("_update_components");
                continue;
            }
            if (clientMessageRecieved.equals("_get_result_from_company")) {
                ict.getResultFromCompany();
                continue;
            }
            if (clientMessageRecieved.equals("_go_back")) {
                sendMessage("_close_wnd");
                menu();
                break;
            } else {
                break;
            }
        } while (true);
    }

    public void menu() throws IOException, ClassNotFoundException {
        if (user.getUserRights().equals("admin")) {
            sendMessage("_menu_admin");
        } else {
            sendMessage("_menu_user");
        }
        sendMessage(employeeNSn);
    }

    public void companyRegistration() throws IOException, SQLException, ClassNotFoundException {
        sendMessage("_company_reg");

        company = new Company();

        query = "INSERT INTO company VALUES(NULL,?,?,?,?,?,?,?)";

        preparedStmt = worker.getConnection().prepareStatement(query);

        do {
            stringmap = receiveStringMap();//считывание данных потока ввода
            company.stringmapToCompany(stringmap);
            serverMessage("stringmap recieved! ");

            int flag = 0;
            for (String key : stringmap.values()) {
                if (key.isEmpty()) {
                    sendMessage("_error_wnd");
                    sendMessage((String) ("<html>Необходимо заполнить все поля!</html>"));
                    flag = 0;
                    break;
                }
                flag++;
            }
            if (flag == 0) {
                continue;
            }

//            if (company.getCompanyName() != null) {
            preparedStmt.setString(1, company.getCompanyName());
//            } else {
//                sendObject("_error_wnd");
//                sendObject((String) ("<html>Введите название компании!</html>"));
//                continue;
//            }

//            if (company.getCompanyLocation() != null) {
            preparedStmt.setString(2, company.getCompanyLocation());
//            } else {
//                sendObject("_error_wnd");
//                sendObject((String) ("<html>Введите адрес компании!</html>"));
//                continue;
//            }

            //if (company.getVAT() != null) {
            preparedStmt.setObject(3, (Double) company.getVAT());
            //}
            //if (company.getSocialCharges() != null) {
            preparedStmt.setObject(4, (Double) company.getSocialCharges());
            //}
            //if (company.getManufCosts() != null) {
            preparedStmt.setObject(5, (Double) company.getManufCosts());
            //}
            //if (company.getGenrunCosts() != null) {
            preparedStmt.setObject(6, (Double) company.getGenrunCosts());
            //}
            //if (company.getInsurCosts() != null) {
            preparedStmt.setObject(7, (Double) company.getInsurCosts());
            //}
            break;
        } while (true);
        // execute the preparedstatement
        preparedStmt.execute();
    }

    public void userRegistration(String rights) throws IOException, SQLException, ClassNotFoundException {

        sendMessage("_user_reg");

        stringmap = receiveStringMap();//считывание данных потока ввода
        user = new User();
        user.stringmapToUser(stringmap);
        employee = new Employee();
        employee.stringmapToEmployee(stringmap);

        query = "INSERT INTO employees VALUES(NULL,?,?)";

        preparedStmt = worker.getConnection().prepareStatement(query);
        preparedStmt.setString(1, employee.getEmployeeSurname());
        preparedStmt.setString(2, employee.getEmployeeName());
        preparedStmt.execute();

        query = "SELECT * FROM employees";
        resultSet = statement.executeQuery(query);
        resultSet.last();

        int emplID = resultSet.getInt(1);

        query = "INSERT INTO users VALUES(NULL,?,?,'" + rights + "',?)";

        preparedStmt = worker.getConnection().prepareStatement(query);
        preparedStmt.setString(1, user.getUserLogin());
        preparedStmt.setString(2, user.getUserPassword());
        preparedStmt.setInt(3, emplID);
        preparedStmt.execute();
    }

    public void userEntering() throws SQLException, IOException, ClassNotFoundException {
        user = new User();
        user.stringmapToUser(stringmap);
        do {

            query = "SELECT * FROM users WHERE `UserLogin` = '" + user.getUserLogin() + "'";
            resultSet = statement.executeQuery(query);

            if (resultSet.last() == false) {
                sendMessage("_error_wnd");
                sendMessage((String) ("<html>Логин не найден!<br>"
                        + "Проверьте введённые данные<br>"
                        + "или зарегистрируйтесь.</html>"));

                stringmap = receiveStringMap();
                user = new User();
                user.stringmapToUser(stringmap);
            } else {
                break;
            }
        } while (true);
        do {

            query = "SELECT * FROM users WHERE `UserLogin` = '" + user.getUserLogin() + "'"
                    + " AND `UserPassword` = '" + user.getUserPassword() + "'";
            resultSet = statement.executeQuery(query);

            if (resultSet.last() == false) {
                sendMessage("_error_wnd");
                sendMessage((String) ("<html>Неверный пароль!<br>"
                        + "Проверьте введённые данные.</html>"));
                stringmap = receiveStringMap();
                user = new User();
                user.stringmapToUser(stringmap);
            } else {
                sendMessage("_close_wnd");
                break;
            }
        } while (true);
    }

    public static String encryptUserPassword(String str) {
        return String.valueOf(str.hashCode());
    }

}
