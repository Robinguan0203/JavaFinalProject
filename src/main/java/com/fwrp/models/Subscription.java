package com.fwrp.models;

/**
 * Class representing a Subscription.
 * 
 * This class contains fields for id, method, and consumer, along with their respective getters and setters.
 * 
 */
public class Subscription {
	
	/**
     * The subscription ID.
     */
    private int id;
    
	/**
     * The method of the subscription.
     */
    private int method;

	/**
     * The consumer associated with the subscription.
     */
    private Consumer consumer;

    /**
     * Gets the subscription ID.
     * 
     * @return the subscription ID
     */
    public int getId() {
        return id;
    }

	/**
     * Sets the subscription ID.
     * 
     * @param id the subscription ID
     */
    public void setId(int id) {
        this.id = id;
    }
	
	/**
     * Gets the method of the subscription.
     * 
     * @return the method of the subscription
     */
    public int getMethod() {
        return method;
    }
	
	/**
     * Sets the method of the subscription.
     * 
     * @param method the method of the subscription
     */
    public void setMethod(int method) {
        this.method = method;
    }
	
	/**
     * Gets the consumer associated with the subscription.
     * 
     * @return the consumer associated with the subscription
     */
    public Consumer getConsumer() {
        return consumer;
    }
	
	/**
     * Sets the consumer associated with the subscription.
     * 
     * @param consumer the consumer associated with the subscription
     */
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
