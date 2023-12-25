public class MoviesCH extends TvChannel{
    int additionalfee = 15;
    public MoviesCH(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice(){
        return super.getPrice() + additionalfee;
    }
}
