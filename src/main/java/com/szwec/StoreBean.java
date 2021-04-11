package com.szwec;

import com.szwec.database.model.Item;
import com.szwec.database.model.Inventory;
import com.szwec.database.service.InventoryManager;
import com.szwec.interceptor.Logged;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class StoreBean implements Serializable {
    public static final String INVENTORY_SERVICE = "java:global/database-server/InventoryService";
    private transient Context context;

    @NotEmpty
    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String sport;

    @NotEmpty
    private int quantity;

    @NotEmpty
    private double price;

    @NotEmpty
    private String date;


    @PostConstruct
    private void createContext() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "org.wildfly.naming.client.WildFlyInitialContextFactory");

        context = new InitialContext(properties);
    }

    private Object lookupdatabase(String databaseName) throws NamingException {
       return context.lookup(databaseName);
    }

    public List<Inventory> getInventories() throws NamingException {
        return ((InventoryManager) lookupdatabase(INVENTORY_SERVICE)).getInventories();
    }

    @Logged
    public void addInventory() throws NamingException, ExecutionException, InterruptedException {
        Inventory inventory = new Item(id, name, sport, quantity, price, date);
        String result = ((InventoryManager) lookupdatabase(INVENTORY_SERVICE)).add(inventory).get();
        if ("Added".equals(result)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item added",
                            "Item was added to the Inventory"));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
