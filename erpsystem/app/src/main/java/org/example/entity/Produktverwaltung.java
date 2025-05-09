package org.example.entity;

import java.math.BigDecimal;

public class Produktverwaltung{
    private String productId;
    private String productName;
    private String supplier;
    private BigDecimal costPrice;
    private BigDecimal retailPrice;
    private Integer stockLevel;

    public Produktverwaltung(String productId, String productName, String supplier, BigDecimal costPrice, BigDecimal retailPrice, Integer stockLevel) {
        this.productId = productId;
        this.productName = productName;
        this.supplier = supplier;
        this.costPrice = costPrice;
        this.retailPrice = retailPrice;
        this.stockLevel = stockLevel;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
        this.stockLevel = stockLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produktverwaltung that = (Produktverwaltung) o;

        if (!productId.equals(that.productId)) return false;
        if (!productName.equals(that.productName)) return false;
        if (!supplier.equals(that.supplier)) return false;
        if (!costPrice.equals(that.costPrice)) return false;
        if (!retailPrice.equals(that.retailPrice)) return false;
        return stockLevel.equals(that.stockLevel);
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + productName.hashCode();
        result = 31 * result + supplier.hashCode();
        result = 31 * result + costPrice.hashCode();
        result = 31 * result + retailPrice.hashCode();
        result = 31 * result + stockLevel.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Produktverwaltung{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", supplier='" + supplier + '\'' +
                ", costPrice=" + costPrice +
                ", retailPrice=" + retailPrice +
                ", stockLevel=" + stockLevel +
                '}';
    }
}