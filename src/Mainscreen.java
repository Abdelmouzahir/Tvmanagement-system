import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Mainscreen extends JFrame {
    //panel1: user registration
    JPanel subscriberPanel;
    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;
    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;

    // panel 2 : Cycle
    JTextField startCycleFLD;
    JTextField endCycleFLD;
    JTextField numberTVFLD;
    JLabel todayLBL;
    JPanel cyclePanel;
    SimpleDateFormat df;
    Date currentDate;
    JLabel startCycleLBL;
    JLabel endCycleLBL;
    JLabel numberTVLBL;

    //Panel3: channels packages
    JCheckBox sportsCHBX;
    JCheckBox moviesCHBX;
    JCheckBox docCHBX;
    JPanel packagesPanel;

    //Panel 4: Package details
    JTextArea channelsAreaS;
    JTextArea channelsAreaM;
    JTextArea channelsAreaD;
    JPanel detailsPanel;

    //Panel 5: check and payment
    JLabel installFeeLBL;
    JPanel feePanel;
    JLabel packagefeeLBL;
    JLabel totalfeeLBL;

    //panel 6 :  Table (Data of subscription)
    JTable table;
    DefaultTableModel tableModel;
    JPanel p6Panel;

    //Panel 7: Action Panel
    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;
    JPanel p7Actionpanel;

    //classes and objects
    Subscriber subscriber;
    Subscription subscription;
    int packagesSelectedPrice = 0;
    int totalPrice =0;

    //saving
    ArrayList<Subscription> listToSave = new ArrayList<>();
    File file;

    //constructor
    public Mainscreen(){
        super("Tv management Company");
        ////////// panel 1/////////
        subscriberPanel = new JPanel();
        Border panel1title = BorderFactory.createTitledBorder("Subscriber details");
        subscriberPanel.setBorder(panel1title);
        subscriberPanel.setBounds(15,15,300,200);
        subscriberPanel.setLayout(new GridLayout(4,2));

        //Jlabel
        nameLBL = new JLabel("First Name: ");
        lastLBL = new JLabel("last name: ");
        mobileLBL = new JLabel("Mobile: ");
        cityLBL = new JLabel("City: ");

        //textFields
        subName = new JTextField();
        subLastName = new JTextField();
        subMobile = new JTextField();
        subCity = new JTextField();

        //adding component to panel1
        subscriberPanel.add(nameLBL);
        subscriberPanel.add(subName);
        subscriberPanel.add(lastLBL);
        subscriberPanel.add(subLastName);
        subscriberPanel.add(mobileLBL);
        subscriberPanel.add(subMobile);
        subscriberPanel.add(cityLBL);
        subscriberPanel.add(subCity);




        ///////Panel 2 //////////
        cyclePanel = new JPanel();
        cyclePanel.setBounds(15,230,300,500);
        cyclePanel.setLayout(new GridLayout(14,1));
        Border cycleBorder = BorderFactory.createTitledBorder("Cycle Details");
        cyclePanel.setBorder(cycleBorder);

        //component of cycle panel
        todayLBL = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate =new Date();
        todayLBL.setText("Today: "+ df.format(currentDate));

        //start cycle date
        startCycleLBL = new JLabel("Start Cycle Date (DD/MM/YYYY)");
        startCycleFLD = new JTextField();

        //end cycle date
        endCycleLBL = new JLabel("End Cycle Date (DD/MM/YYYY)");
        endCycleFLD = new JTextField();

        //number of tvs
        numberTVLBL = new JLabel("Number of TV: ");
        numberTVFLD = new JTextField();

        cyclePanel.add(todayLBL);
        cyclePanel.add(startCycleLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endCycleLBL);
        cyclePanel.add(endCycleFLD);
        cyclePanel.add(numberTVLBL);
        cyclePanel.add(numberTVFLD);

        //make opacity for field
        startCycleFLD.setOpaque(false);
        endCycleFLD.setOpaque(false);
        numberTVFLD.setOpaque(false);

        ////////panel 3//////////////
        packagesPanel = new JPanel();
        packagesPanel.setBounds(330,15,300,200);
        packagesPanel.setLayout(new GridLayout(5,1));

        Border packBorder = BorderFactory.createTitledBorder("Available Channel");
        packagesPanel.setBorder(packBorder);

        JLabel packageLBL = new JLabel("Please select your Package");

        sportsCHBX = new JCheckBox("Sports package");
        moviesCHBX = new JCheckBox("Movies Package");
        docCHBX = new JCheckBox("documentary Package");

        JButton subscribeBTN =new JButton("Subscribe");

        packagesPanel.add(packageLBL);
        packagesPanel.add(sportsCHBX);
        packagesPanel.add(moviesCHBX);
        packagesPanel.add(docCHBX);
        packagesPanel.add(subscribeBTN);

        //checkbox Item Listeners
        sportsCHBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (sportsCHBX.isSelected()){
                    DisplaySportsChannels();
                    //make the price change
                }else{
                    channelsAreaS.setText("");
                }
            }
        });

        moviesCHBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (moviesCHBX.isSelected()){
                    DisplayMoviesChannels();
                }else{
                    channelsAreaM.setText("");
                }
            }
        });

        docCHBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (docCHBX.isSelected()){
                    DisplaydocChannels();
                }else{
                    channelsAreaD.setText("");

                }
            }
        });

        subscribeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    GetSubscriberData();
                }catch(Exception ee){

                }
            }
        });
      ///////////////////// Panel 4 //////////////////////////////
        detailsPanel = new JPanel();
        detailsPanel.setBounds(330,230,300,500);
        detailsPanel.setLayout(new GridLayout(3,1));

        Border p4Border = BorderFactory.createTitledBorder("Available Channels");
        detailsPanel.setBorder(p4Border);

        channelsAreaS = new JTextArea(5,1);
        channelsAreaS.setEditable(false);
        channelsAreaS.setOpaque(false);
        channelsAreaS.setLineWrap(true);

        channelsAreaM = new JTextArea(5,1);
        channelsAreaM.setEditable(false);
        channelsAreaM.setOpaque(false);
        channelsAreaM.setLineWrap(true);

        channelsAreaD = new JTextArea(5,1);
        channelsAreaD.setEditable(false);
        channelsAreaD.setOpaque(false);
        channelsAreaD.setLineWrap(true);

        detailsPanel.add(channelsAreaS);
        detailsPanel.add(channelsAreaM);
        detailsPanel.add(channelsAreaD);

       /////////////Panel 5////////////////////////
        feePanel = new JPanel();
        feePanel.setBounds(645,15,200,200);
        feePanel.setLayout(new GridLayout(3,1));

        Border blackline5 = BorderFactory.createTitledBorder("Fee & Check");
        feePanel.setBorder(blackline5);

        installFeeLBL = new JLabel("Installation Fee: ");
        packagefeeLBL = new JLabel("Packages Fee: ");
        totalfeeLBL = new JLabel("Total Amount to Pay: ");

        feePanel.add(installFeeLBL);
        feePanel.add(packagefeeLBL);
        feePanel.add(totalfeeLBL);


        ////////////////////////////////Panel 6 /////////////////////////////////////////////////
        p6Panel = new JPanel();
        p6Panel.setBounds(645,230,515,500);
        p6Panel.setLayout(new GridLayout(3,1));

        Border blackline6 = BorderFactory.createTitledBorder("Customer infos");
        p6Panel.setBorder(blackline6);

        // Table:
        //1- Table Model
        tableModel  = new DefaultTableModel();

        //2- columns
        table = new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");

        //3-scrool pane
        JScrollPane scrollPane = new JScrollPane(table);
        p6Panel.add(scrollPane);

        ///////////////////Panel 7 ///////////////////////////////////
        p7Actionpanel = new JPanel();
        p7Actionpanel.setBounds(860,15,300,200);
        p7Actionpanel.setLayout(new GridLayout(4,1));


        Border blackline7 = BorderFactory.createTitledBorder("Action Tab");
        p7Actionpanel.setBorder(blackline7);

        saveBTN = new JButton("Save Subscription");
        loadBTN = new JButton("Load Subscription");
        newBTN = new JButton("New Subscription");

        p7Actionpanel.add(newBTN);
        p7Actionpanel.add(saveBTN);
        p7Actionpanel.add(loadBTN);

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptionToDisk();
            }
        });

        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }

        });

        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Subscription> k = LoadSuscription();
            }


        });

        //adding panel to Jframe
        setLayout(null); //null layout for Jframe;
        add(subscriberPanel); //panel 1
        add(cyclePanel); //panel 2
        add(packagesPanel);//panel 3
        add(detailsPanel);//panel 4
        add(feePanel); //panel 5
        add(p6Panel); //panel 6
        add(p7Actionpanel); //panel 7

    }
    /////////////////////////////METHODS///////////////////////////
    private int DisplaySportsChannels() {
        SportCH s1 = new SportCH("BEIN Sport", "AR","SP",5);
        SportCH s2 = new SportCH("ESPN", "US", "SP", 4);
        SportCH s3 = new SportCH("Sky Sports", "UK", "SP", 5);
        SportCH s4 = new SportCH("SuperSport", "ZA", "SP", 4);
        SportCH s5 = new SportCH("SporTV", "BR", "SP", 3);
        SportCH s6 = new SportCH("EuroSport", "FR", "SP", 4);

        ArrayList<SportCH> sportChannels = new ArrayList<>();
        sportChannels.add(s1);
        sportChannels.add(s2);
        sportChannels.add(s3);
        sportChannels.add(s4);
        sportChannels.add(s5);
        sportChannels.add(s6);

        String sptChString = "";
        int packageprice = 0;
        for(int i=0; i<sportChannels.size(); i++){
            sptChString +=
                             "    "+sportChannels.get(i).getChannelName()
                            +"    "+sportChannels.get(i).getLanguage()
                            +"    "+sportChannels.get(i).getPrice()
                            +"\n";
            packageprice += sportChannels.get(i).getPrice();
        }
        channelsAreaS.setText(sptChString);
        return packageprice;
    }
    private int DisplayMoviesChannels() {
        MoviesCH m1 = new MoviesCH("MBC Bundle", "SP", "MOV",4);
        MoviesCH m2 = new MoviesCH("Hollywood Classics", "US", "MOV", 5);
        MoviesCH m3 = new MoviesCH("Bollywood Hits", "IN", "MOV", 4);
        MoviesCH m4 = new MoviesCH("French Cinema", "FR", "MOV", 3);
        MoviesCH m5 = new MoviesCH("Japanese Film Fest", "JP", "MOV", 4);
        MoviesCH m6 = new MoviesCH("Latin American Cinema", "MX", "MOV", 3);

        ArrayList<MoviesCH> moviesChannels = new ArrayList<>();
        moviesChannels.add(m1);
        moviesChannels.add(m2);
        moviesChannels.add(m3);
        moviesChannels.add(m4);
        moviesChannels.add(m5);
        moviesChannels.add(m6);

        String movChString = "";
        int packageprice = 0;
        for(int i=0; i<moviesChannels.size(); i++){
            movChString +=
                             "    "+moviesChannels.get(i).getChannelName()
                            +"    "+moviesChannels.get(i).getLanguage()
                            +"    "+moviesChannels.get(i).getPrice()
                            +"\n";
            packageprice += moviesChannels.get(i).getPrice();

        }
        channelsAreaM.setText(movChString);
        return packageprice;
    }
    private int DisplaydocChannels() {
        DocumentaryCH d1 = new DocumentaryCH("NAT GEO", "SP", "DOC",3);
        DocumentaryCH d2 = new DocumentaryCH("Discovery", "US", "EDU", 2);
        DocumentaryCH d3 = new DocumentaryCH("BBC Earth", "UK", "NAT", 4);
        DocumentaryCH d4 = new DocumentaryCH("History Channel", "CA", "HIS", 1);
        DocumentaryCH d5 = new DocumentaryCH("Animal Planet", "AU", "ANI", 5);
        DocumentaryCH d6 = new DocumentaryCH("Science Channel", "IN", "SCI", 2);

        ArrayList<DocumentaryCH> documentaryChannels = new ArrayList<>();
        documentaryChannels.add(d1);
        documentaryChannels.add(d2);
        documentaryChannels.add(d3);
        documentaryChannels.add(d4);
        documentaryChannels.add(d5);
        documentaryChannels.add(d6);

        String docChString = "";
        int packageprice = 0;
        for(int i=0; i<documentaryChannels.size(); i++){
            docChString +=
                     "    "+documentaryChannels.get(i).getChannelName()
                    +"    "+documentaryChannels.get(i).getLanguage()
                    +"    "+documentaryChannels.get(i).getPrice()
                    +"\n";
            packageprice += documentaryChannels.get(i).getPrice();
        }
        channelsAreaD.setText(docChString);
        return packageprice;
    }
    private void GetSubscriberData() throws ParseException {
        Date currrentDate = new Date();

        //Subscriber Data:
        subscriber = new Subscriber(
                subName.getText(),
                subLastName.getText(),
                subCity.getText(),
                Integer.parseInt(subMobile.getText()));
        //Cycle
        Date startCycle = df.parse(startCycleFLD.getText());
        Date endCycle =  df.parse(endCycleFLD.getText());

        SubscriptionCycle cycle = new SubscriptionCycle(
                df.format(startCycle),
                df.format(endCycle)
        );

        //subscription
        subscription = new Subscription(
                Integer.parseInt(numberTVFLD.getText()),
                subscriber,
                cycle,
                df.format(currentDate)
        );

        installFeeLBL.setText("Installation Fee: "+ subscription.getTotalFee()+ " $");
        ShowPrice();

    }

    private void ShowPrice() {
        if(docCHBX.isSelected())
            packagesSelectedPrice += DisplaydocChannels();
        else if(moviesCHBX.isSelected()){
            packagesSelectedPrice += DisplayMoviesChannels();
        }
        else if (sportsCHBX.isSelected()) {
            packagesSelectedPrice += DisplaySportsChannels();
        }
        packagefeeLBL.setText("Packages Fee: "+ packagesSelectedPrice + " $");
        totalPrice = subscription.getTotalFee()+ packagesSelectedPrice;

        totalfeeLBL.setText("Total Amount to pay: "+ totalPrice+ " $");
    }

    private void SaveSubscriptionToDisk() {
        listToSave.add(subscription);
        file = new File("D:\\myfile.dat");
            try {
               OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                //saving the list of subscriptions
                oos.writeObject(listToSave);
                oos.flush();
                oos.close();
                os.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    private void NewSubscription() {
        //All field are empty
        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobile.setText("");

        startCycleFLD.setText("");
        endCycleFLD.setText("");
        numberTVFLD.setText("");

        installFeeLBL.setText("Installation Fee: ");
        packagefeeLBL.setText("Packages Fee: ");
        totalfeeLBL.setText("Total Amount to pay: ");

        moviesCHBX.setSelected(false);
        docCHBX.setSelected(false);
        sportsCHBX.setSelected(false);
    }
    private ArrayList<Subscription> LoadSuscription() {
        ArrayList<Subscription> s = new ArrayList<>();
        file = new File("D:\\myfile.dat");
        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);

            s = (ArrayList) ois.readObject();
            ois.close();
            is.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        for (Subscription sub: s){
            DisplaySubInTable(sub);
        }
        return s;
    }

    private void DisplaySubInTable(Subscription sub) {
        //displaying data from disk on the table
        tableModel.addRow(new Object[]{
                          sub.getSubscriber().getfName(),
                          sub.getSubscriber().getlName(),
                          sub.getSubscriber().getPhone(),
                          sub.getCycle().getStartDate(),
                          sub.getCycle().getEndDate(),
                          totalPrice
        });
    }

    public static void main(String[] args){

        Mainscreen mainscreen = new Mainscreen();
        mainscreen.setVisible(true);
        mainscreen.setBounds(20,10,1200,800);
    }
}
