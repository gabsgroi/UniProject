import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {

    public static void main(String[] args) throws IOException {

        ListOrder listorder=readFile();
        FileWriter writer= new FileWriter("ciaone.txt",true); //mettere append true
        PrintWriter pw=new PrintWriter(writer);
        for(Order o:listorder.getOrderlist()){
            pw.print(o.getOrder_id());
            pw.print(";");
            pw.print(o.getStartdate());
            pw.print(";");
            pw.print(o.getStatus());
            pw.print(";");
            pw.print(o.getReceiver().getName());
            pw.print(";");
            pw.print(o.getReceiver().getAddress());
            pw.print(";");
            pw.print(o.getSender().getName());
            pw.print(";");
            pw.print(o.getSender().getAddress());
            pw.print(";");
            pw.print(o.getPacklist().getPacklist().size());
            pw.print(";");
            for (Pack p : o.getPacklist().getPacklist()){
                pw.print(p.getLenght());
                pw.print(";");
                pw.print(p.getWidth());
                pw.print(";");
                pw.print(p.getDepth());
                pw.print(";");
                pw.print(p.getWeight());
                pw.print(";");
            }
            pw.flush();
            pw.print('\n');
        }
    }
    public synchronized static ListOrder readFile() throws IOException {
        FileReader reader =new FileReader("ciao.txt");
        Scanner in=new Scanner(reader);
        ListOrder tmp_order=new ListOrder();
        while(in.hasNext()){
            String line=in.nextLine();
            String [] vectorline=line.split(";");
            String tmp_id=vectorline[0];
            Long tmp_date=Long.parseLong(vectorline[1]);
            String tmp_status=vectorline[2];
            String tmp_receivername=vectorline[3];
            String tmp_receiveraddress=vectorline[4];
            String tmp_sendername=vectorline[5];
            String tmp_senderaddress=vectorline[6];
            Integer index =Integer.parseInt(vectorline[7]);
            PackList tmp_packlist=new PackList();
            ArrayList <Float> tmp_pack = new ArrayList<>();
            ArrayList<Pack> packlist=new ArrayList<>();
            for (int i=1;i<=index*4;i++) {
                tmp_pack.add(Float.parseFloat(vectorline[7+i]));
                if (i%4==0){
                    Pack p=new Pack(tmp_pack.get(i-4),tmp_pack.get(i-3),tmp_pack.get(i-2),tmp_pack.get(i-1));
                    tmp_packlist.addPack(p);
                }
            }
            Receiver tmp_receiver=new Receiver(tmp_receivername,tmp_receiveraddress);
            Sender tmp_sender=new Sender(tmp_sendername,tmp_senderaddress);
            Order o = new Order(UUID.fromString(tmp_id),tmp_date,tmp_status,tmp_receiver,tmp_sender,tmp_packlist);
            tmp_order.addOrder(o);
        }
        reader.close();
        return tmp_order;
    }
}
