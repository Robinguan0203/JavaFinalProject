package com.fwrp.models;

import java.util.Date;

/**
 * Represents an Order.
 * This class implements the InventoryChange interface and includes properties and methods related to an order.
 */
public class Order implements InventoryChange{
	
	/**
     * The order ID.
     */
    private int id;
	
	/**
     * The consumer associated with the order.
     */
    private Consumer consumer;
	
	/**
     * The food item associated with the order.
     */
    private Food food;
	
	/**
     * The date of the order.
     */
    private Date date;
	
	/**
     * The quantity discount for the order.
     */
    private int qtyDiscount;
    
	/**
     * Default constructor.
     */
    public Order(){
        
    }
    
	/**
     * Constructs an Order with the specified consumer, food, date, and quantity discount.
     * 
     * @param consumer the consumer associated with the order.
     * @param food the food item associated with the order.
     * @param date the date of the order.
     * @param qtyDiscount the quantity discount for the order.
     */
    public Order(Consumer consumer, Food food,
            Date date, int qtyDiscount){
        this.consumer = consumer;
        this.food = food;
        this.date = date;
        this.qtyDiscount = qtyDiscount;
    }
    
	/**
     * Constructs an Order with the specified ID, consumer, food, date, and quantity discount.
     * 
     * @param id the order ID.
     * @param consumer the consumer associated with the order.
     * @param food the food item associated with the order.
     * @param date the date of the order.
     * @param qtyDiscount the quantity discount for the order.
     */
    public Order(int id, Consumer consumer, Food food,
            Date date, int qtyDiscount){
        this.id = id;
        this.consumer = consumer;
        this.food = food;
        this.date = date;
        this.qtyDiscount = qtyDiscount;
    }

    /**
     * Gets the order ID.
     * 
     * @return the order ID.
     */
    public int getId() { return id; }
	
	/**
     * Sets the order ID.
     * 
     * @param id the order ID.
     */
    public void setId(int id) { this.id = id; }

	/**
     * Gets the consumer associated with the order.
     * 
     * @return the consumer.
     */
    public Consumer getConsumer() { return consumer; }
	
	/**
     * Sets the consumer associated with the order.
     * 
     * @param consumer the consumer.
     */
    public void setConsumer(Consumer consumer) { this.consumer = consumer; }

	/**
     * Gets the food item associated with the order.
     * 
     * @return the food item.
     */
    public Food getFood() { return food; }
	
	/**
     * Sets the food item associated with the order.
     * 
     * @param food the food item.
     */
    public void setFood(Food food) { this.food = food; }

	/**
     * Gets the date of the order.
     * 
     * @return the date.
     */
    public Date getDate() { return date; }
	
	/**
     * Sets the date of the order.
     * 
     * @param date the date.
     */
    public void setDate(Date date) { this.date = date; }
	
	/**
     * Gets the quantity discount for the order.
     * 
     * @return the quantity discount.
     */
    public int getQtyDiscount() { return qtyDiscount; }
	
	/**
     * Sets the quantity discount for the order.
     * 
     * @param qtyDiscount the quantity discount.
     */
    public void setQtyDiscount(int qtyDiscount) { this.qtyDiscount = qtyDiscount; }

	/**
     * Creates a transaction for the order.
     * 
     * @return a new OrderTransaction object.
     */
	public OrderTransaction createTransaction() {
        OrderTransaction transaction = null;
        Claim claim = null;
        Order order = this;
        int qtyNormal = 0;
        int qtyDonation = 0;
        int this_qtyDiscount = this.getQtyDiscount();
        
        OrderTransactionCreator creator = new OrderTransactionCreator();
        transaction = creator.createTransaction(
                this.getFood(),
                this.getConsumer(),
                order,
                claim,
                qtyNormal, 
                - this_qtyDiscount, 
                qtyDonation
        );
        
        return transaction;
    } 
}
