package classes.Presents;

import enums.Category;

public class Gift {
    private String productName;
    private Double price;
    private Category category;
    private Integer quantity;

    /**
     * Default constructor
     */
    public Gift() {

    }

    /**
     * Copy-constructor
     * @param gift
     *      cadoul
     */
    public Gift(final Gift gift) {
        this.productName = gift.getProductName();
        this.price = gift.getPrice();
        this.category = gift.getCategory();
        //!!
        this.quantity = gift.accessQuantity();
    }

    /**
     * Getter pentru productName
     * @return
     *      numele produsului
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Setter pentru productName
     * @param productName
     *      numele produsului
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    /**
     * Getter pentru price
     * @return
     *      pretul produsului
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Setter pentru price
     * @param price
     *      pretul produsului
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * Getter pentru category
     * @return
     *      categoria produsului
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Setter pentru category
     * @param category
     *      categoria produsului
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    public Integer accessQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
