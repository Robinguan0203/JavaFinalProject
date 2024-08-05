package com.fwrp.models;

/**
 * Represents a user preference in the system.
 * <p>
 * This class maps a user preference to a specific food item and consumer.
 * </p>
 * 
 * @version 1.0
 * @since 17.0.8
 */
public class Preference {
    
    /**
     * The unique identifier for the preference.
     */
    private int id;    
    
    /**
     * The food item associated with the preference.
     */
    private Food food;
    
    /**
     * The consumer associated with the preference.
     */
    private Consumer consumer;

    /**
     * Gets the unique identifier for the preference.
     *
     * @return int The preference ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the preference.
     *
     * @param id The preference ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the food item associated with the preference.
     *
     * @return Food The food item.
     */
    public Food getFood() {
        return food;
    }

    /**
     * Sets the food item associated with the preference.
     *
     * @param food The food item.
     */
    public void setFood(Food food) {
        this.food = food;
    }

    /**
     * Gets the consumer associated with the preference.
     *
     * @return Consumer The consumer.
     */
    public Consumer getConsumer() {
        return consumer;
    }

    /**
     * Sets the consumer associated with the preference.
     *
     * @param consumer The consumer.
     */
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}
