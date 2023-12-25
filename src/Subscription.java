import java.io.Serializable;
public class Subscription implements Serializable {
    private int installFee; //fixed fee         10$/TV
    private int nbTV;
    private Subscriber subscriber;
    private SubscriptionCycle cycle;
    private  String datee;

    //Total fee
    private int totalFee;

    public Subscription(int nbTV, Subscriber subscriber, SubscriptionCycle cycle, String datee) {
        this.nbTV = nbTV;
        this.subscriber = subscriber;
        this.cycle = cycle;
        this.datee = datee;
        this.installFee = nbTV * 10;
    }

    public int getNbTV() {
        return nbTV;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public SubscriptionCycle getCycle() {
        return cycle;
    }

    public String getDatee() {
        return datee;
    }

    public int getTotalFee() {
        totalFee = installFee + 5;
        return totalFee;
    }

    public void setNbTV(int nbTV) {
        this.nbTV = nbTV;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void setCycle(SubscriptionCycle cycle) {
        this.cycle = cycle;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }



    @Override
    public String toString() {
        return "Subscription{" +
                "installFee=" + installFee +
                ", nbTV=" + nbTV +
                ", subscriber=" + subscriber +
                ", cycle=" + cycle +
                ", datee='" + datee + '\'' +
                ", totalFeel=" + totalFee +
                '}';
    }
}
