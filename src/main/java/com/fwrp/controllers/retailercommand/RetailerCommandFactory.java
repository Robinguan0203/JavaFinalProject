/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

/**
 *
 * @author robin
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
