/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

/**
 * This class is a factory for creating retailer command objects based on the provided action string.
 * <p>
 * It maps action strings to specific command implementations that handle various retailer operations.
 * </p>
 * 
 * <p>Note: Each command class should implement the {@link IRetailerCommand} interface.</p>
 * 
 * @param action the action string that determines which command to create
 * @return an instance of a class that implements {@link IRetailerCommand} based on the action string *  
 * 
 * <p>Note: Each command class should implement the {@link IRetailerCommand} interface.</p>
 * 
 * @param action the action string that determines which command to create
 * @return an instance of a class that implements {@link IRetailerCommand} based on the action string
 *
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class RetailerCommandFactory {
    public static IRetailerCommand getCommand(String action) {
        switch (action) {
            case "addFood":
                return new AddFoodCommand();
            case "storeNewFood":
                return new StoreNewFoodCommand();
            case "addQuantities":
                return new AddQuantitiesCommand();
            case "storeAddQuantity":
                return new StoreAddQuantityCommand();
            case "changeFoodExpireDays":
                return new ChangeFoodExpireDaysCommand();
            case "storeExpireDays":
                return new StoreExpireDaysCommand();
            case "updateInventoryExpireDate":
                return new UpdateInventoryExpireDateCommand();
            case "storeUpdateExpireDate":
                return new StoreUpdateExpireDateCommand();
            case "identifySurplus":
                return new IdentifySurplusCommand();
            case "storeUpdateIsSurplus":
                return new StoreUpdateIsSurplusCommand();
            case "listSurplus":
                return new ListSurplusCommand();
            case "storeListSurplus":
                return new StoreListSurplusCommand();
            case "viewInventory":
                return new ViewInventoryCommand();
            case "viewTransactions":
                return new ViewTransactionsCommand();
            default:
                return new UnknownCommand(); // 处理未知操作
        }
    }
}
