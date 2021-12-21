package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;

import java.util.regex.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.Object;
import javafx.util.Pair;
import java.util.concurrent.TimeUnit;
/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author dnkhanh45
 */
public class DepositController extends BaseController{

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(DepositController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(),
                    cartMedia.getQuantity(),
                    cartMedia.getPrice());
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }

    /**
     * The method validates the info
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{

    }

    /**
     * Kiem tra so dien thoai co hop le khong
     * @param phoneNumber
     * @return
     */
    public boolean validateCardNumber(String phoneNumber) {
        // TODO: your work
        if ((phoneNumber == null) || (phoneNumber.isEmpty())) {
            return false;
        }
        if (phoneNumber.charAt(0) != '0') {
            return false;
        }
        if ((phoneNumber.length() > 10) || (phoneNumber.length() < 9)) {
            return false;
        }
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * Kiem tra ten nguoi nhan co hop le khong
     * @param name
     * @return
     */
    public boolean validateCardHolderName(String name) {
        // TODO: your work
        if ((name == null) || (name.isEmpty())) {
            return false;
        }
        String regex = "[^aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢ"
                + "pPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        return !m.find();
    }


    /**
     * Kiem tra ten nguoi nhan co hop le khong
     * @param name
     * @return
     */
    public boolean validateExpirationDate(String name) {
        // TODO: your work
        if ((name == null) || (name.isEmpty())) {
            return false;
        }
        String regex = "[^aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
                + "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢ"
                + "pPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        return !m.find();
    }

    /**
     * Kiem tra thoi gian nhan hang ky vong khi giao hang nhanh co hop le khong
     * @param expectedTime
     * @return
     */
    public Pair<Boolean, Date> validateExpectedTime(String expectedTime) {
        if ((expectedTime == null) || (expectedTime.isEmpty())) {
            return new Pair<Boolean, Date>(false, null);
        }
        String[] patterns = {"yyyy-MM-dd HH", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:SS"};
        Date expectedDate = null;
        for (String pattern : patterns) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                expectedDate = format.parse(expectedTime);
                break; //if your date is correct break your look
            } catch (ParseException e) {}
        }
        if (expectedDate == null) {
            return new Pair<Boolean, Date>(false, null);
        }
        return new Pair<Boolean, Date>(true, expectedDate);
    }

    /**
     * Kiem tra thoi gian giao hang nhanh co thuoc khoang 0 den 10 ngay
     * @param expectedDate
     * @return
     */
    public boolean validateRushTimeDiff(Date expectedDate) {
        long milliesSecondDiff = Math.abs(expectedDate.getTime() - new Date().getTime());
        long dateDiff = TimeUnit.DAYS.toDays(milliesSecondDiff);
        if ((dateDiff < 0) || (dateDiff > 10)) {
            return false;
        }
        return true;
    }

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
