package controller;

import entity.order.Order;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Control the flow of place rush order use case in AIMS
 * @author duclong1009
 */
public class PlaceRushOrderController extends  PlaceOrderController{
    /**
     *
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

    /**
     * Kiem tra ngay gia hang nhanh du kiem co phai la so nguyen va lon hon 0 khong/
     * @param rushDeliveryDay
     * @return
     */
    public boolean validateRushDeliveryDay(String rushDeliveryDay) {
        try{
            int rushDay = Integer.parseInt(rushDeliveryDay);
            if(rushDay < 1) return false;
        }
        catch (Exception e) {
            return  false;
        }
        return true;
    }

    /**
     * Kiem tra dia chi giao hang nhanh kha thi khong
     * @param address
     * @return
     */
    public boolean validateDeliveryAddress(String address) {
        if(address == null) return false;
        if(address.equals(new String("Hà Nội")) )return true;
        return false;
    }

    /**
     * Tinh phi van chuyen bang phi van chuyen thong thuong cong voi phi gia tang do van chuyen nhanh.
     * @param order
     * @param rushDay
     * @return
     */
    public int calculateShippingFee(Order order,String rushDay) {
        Random rand = new Random();
        int fees = calculateShippingFee(order);
        int additionalFee = (int)( ( (rand.nextFloat()*10)/100 ) * (20 - Integer.parseInt(rushDay)));
        int totalFee = fees + additionalFee;
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + totalFee);
        return totalFee;
    }

}
