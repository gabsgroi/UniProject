import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        // usual stuff here... menu, etc...

        String address = args[0];
        String rmi_name = args[1];

        try {
            Services server = (Services) Naming.lookup("rmi://"+address+"/"+rmi_name);

            System.out.println("Welcome to Sgroi's online shipping service");
            System.out.println(server.getDate());
            ListOrder tmp_listorder = new ListOrder();
            PackList tmp_packlist=new PackList();
            boolean go = true;
            Scanner user_input = new Scanner(System.in);
            Scanner user_input2 = new Scanner(System.in);
            Scanner user_input3 = new Scanner(System.in);
            while (go) {
                System.out.println("--------------------------------");
                System.out.println(" 0 - Login");
                System.out.println(" 1 - Subscrive");
                System.out.println(" 2 - Staff Login");
                System.out.println(" 3 - Quit");
                System.out.println("--------------------------------");

                int choice = user_input.nextInt();

                switch (choice) {
                    case 0:
                        System.out.println("Insert UserId");
                        String userId=user_input.next();
                        System.out.println("Insert Password");
                        String password=user_input.next();
                        User tmp_user= server.searchUser(userId,password);
                        if (tmp_user == null){
                            System.out.println("UserId: "+userId+" not found");
                        }
                        else {
                            boolean menu=true;
                            while (menu){
                                System.out.println("Hello "+tmp_user.getName());
                                System.out.println(" 0 - Insert new order");
                                System.out.println(" 1 - Watch your active orders");
                                System.out.println(" 2 -   ");
                                System.out.println(" 3 - Log Out");
                                System.out.println("--------------------------------");
                                int choice2 = user_input.nextInt();
                                user_input.nextLine(); //to elim..
                                switch (choice2){
                                    case 0:
                                        System.out.println("Insert the receiver's name");
                                        String name=user_input.nextLine();
                                        System.out.println("Insert the receiver's address");
                                        address=user_input.nextLine();
                                        System.out.println("How many pack do you want to deliver?");
                                        int packnumber=user_input.nextInt();
                                        user_input.nextLine(); //to elim..
                                        for (int i=0;i<packnumber; i++){
                                            System.out.println("Insert the lenght, the width, the depth and the weight of your Pack "+(i+1)+": ");
                                            String pack_parameters= user_input.nextLine();
                                            Scanner pack_scanner= new Scanner(pack_parameters);
                                            float lenght = pack_scanner.nextFloat();
                                            float width = pack_scanner.nextFloat();
                                            float depth = pack_scanner.nextFloat();
                                            float weight = pack_scanner.nextFloat();
                                            Pack tmp_pack = new Pack(lenght,width,depth,weight);
                                            tmp_packlist.addPack(tmp_pack);
                                        }
                                        Sender tmp_sender= new Sender(tmp_user.getName(),tmp_user.getAddress());
                                        Receiver tmp_receiver= new Receiver(name,address);
                                        Order tmp_order = new Order(server.getDate(),tmp_receiver,tmp_sender,tmp_packlist);
                                        tmp_listorder.addOrder(tmp_order);
                                        tmp_user.setListorder(tmp_listorder);

                                        System.out.println("--------------");
                                        if(server.addListOrder(tmp_user.getUserid(), tmp_user.getListorder())){
                                            System.out.println("Order correctly added!"+"\n"+tmp_user.getListorder());
                                        }
                                        tmp_listorder=new ListOrder();
                                        tmp_packlist=new PackList();
                                        break;
                                    case 1:
                                        tmp_user=server.searchUser(tmp_user.getUserid(),tmp_user.getPassword());
                                        System.out.println(tmp_user.getListorder().toString());
                                        break;
                                    case 2:

                                    case 3:
                                        menu=false;
                                        System.out.println("Goodbye "+tmp_user.getName());
                                        break;
                                }
                            }
                        }

                        break;
                    case 1:
                            user_input.nextLine(); //to eliminate the new line character
                            System.out.println("Insert your complete Name");
                            String name= user_input.nextLine();
                            System.out.println("Insert your Address");
                            String home_address=user_input.nextLine();
                            System.out.println("Insert your Age");
                            int age = user_input.nextInt();
                            if (age<18){
                                System.out.println("You must be over 18 years old!");
                                break;
                            }
                            System.out.println("Insert your UserId");
                            String userId2= user_input.next();
                            System.out.println("Insert your Password");
                            String password2= user_input.next();
                            User tmp_user2= new User(name,age,password2,home_address,userId2);
                            if(server.addUser(tmp_user2)==false){
                                System.out.println("The User: "+tmp_user2.getUserid()+" already exist!");
                            }
                            else if(server.searchUser(tmp_user2.getUserid(),tmp_user2.getPassword()).compareTo(tmp_user2)==1){
                                System.out.println("User: "+tmp_user2.getUserid()+" "+"added correctly");
                            }
                            else {
                                System.out.printf("Uknown error");
                            }

                        break;
                    case 2:
                        System.out.println("Insert UserId");
                        userId=user_input.next();
                        System.out.println("Insert Password");
                        password=user_input.next();
                        System.out.println("Inser the Staff code");
                        String staff_code=user_input.next();
                        if (server.staffVerify(staff_code)){
                            User tmp_user3= server.searchUser(userId,password);
                            if (tmp_user3 == null){
                                System.out.println("UserId: "+userId+" not found");
                                break;
                            }
                            boolean menu1=true;
                            int choice1;
                            while(menu1){
                                System.out.println("Hello "+tmp_user3.getName());
                                System.out.println(" 0 - Watch client's orders");
                                System.out.println(" 1 - Complete an order");
                                System.out.println(" 2 -   ");
                                System.out.println(" 3 - Log Out");
                                System.out.println("--------------------------------");
                                choice1= user_input.nextInt();
                                switch (choice1){
                                    case 0:

                                        for(String key: server.UserMap().keySet()){
                                            System.out.println(server.UserMap().get(key).getListorder());
                                        }
                                        break;

                                    case 1:
                                        break;

                                    case 2:
                                        break;

                                    case 3:
                                        menu1=false;
                                        System.out.println("Goodbye "+tmp_user3.getName());
                                        break;
                                }
                            }
                        }
                        else {
                            System.out.println("Wrong Staff code");
                            break;
                        }



                        break;
                    case 3:
                        go = false;
                        System.out.println("Quitting client");
                        break;

                }
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}