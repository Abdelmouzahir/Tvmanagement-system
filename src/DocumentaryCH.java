public class DocumentaryCH extends TvChannel{
    int additionalfee = 12;
    public DocumentaryCH(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice(){
        return super.getPrice() + additionalfee;
    }
}
