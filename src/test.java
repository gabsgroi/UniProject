import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class test {

    public static void main(String[] args) {
       /* User p= new User("Gabriele",20,"123456","via delle palme","asdone") ;

    User d= new User("Turi",230,"asdasddd","via delle java","ciaone") ;
    Pack pack1=new Pack(11.3f,22,44.2f,44);
    Pack pack2=new Pack(121.34f,44,11.2f,33);
    PackList packlist=new PackList();
    packlist.addPack(pack1);
    packlist.addPack(pack2);
    Receiver alfio = new Receiver (d.getName(),d.getAddress());
    Sender gab= new Sender(p.getName(),p.getAddress());
    Order order = new Order("11/3/20","12/03/20",alfio,gab,packlist);
    ListOrder orderlist=new ListOrder();
    orderlist.addOrder(order);
    p.setListorder(orderlist);
    UserList lista=new UserList();
    lista.addUser(p);
    lista.addUser(d);

    System.out.printf(p.toString());
        System.out.printf(d.toString());
        Scanner user_input = new Scanner(System.in);
        //user_input.*/
UUID turi = UUID.randomUUID();
        System.out.println(turi);
        Scanner jojo= new Scanner(System.in);
        String id=jojo.nextLine();
        UUID idcod=UUID.fromString(id);
        System.out.println(turi.compareTo(idcod));
        Date a = new Date();
        //  System.out.println(order_id+"\n"+order_id1+"\n"+order_id2+"\n"+order_id3+"\n"+order_id4+"\n");

}
}
