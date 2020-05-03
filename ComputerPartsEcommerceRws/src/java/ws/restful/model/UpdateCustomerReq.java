/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.ComputerPart;
import entity.LineItem;
import java.util.List;

/**
 *
 * @author zeplh
 */
public class UpdateCustomerReq {
    
    private Long userId;
    
    // details
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String contactNumber;
    private String cardNumber;
    private String ccv;
    
    // password
    private String password;
    
    // loyalty points
    private Integer loyaltyPoints;
        
    // cart
    private List<Long> cartProductIds;
    private List<Integer> cartProductQuantities;
    // each list in the list represents the computer part ids of one custom computer set 
    private List<List<Long>> cartComputerSetsPartIds;
    private List<Integer> cartComputerSetsQuantities; 
    
    // current computer build product
    private List<Long> currComputerBuildComputerPartIds;
    
    public UpdateCustomerReq() {
    }

    // details
    public UpdateCustomerReq(Long userId, String firstName, String lastName, String address, String email, String contactNumber, String cardNumber, String ccv) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.contactNumber = contactNumber;
        this.cardNumber = cardNumber;
        this.ccv = ccv;
    }

    // password
    public UpdateCustomerReq(Long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    // loyalty points
    public UpdateCustomerReq(Long userId, Integer loyaltyPoints) {
        this.userId = userId;
        this.loyaltyPoints = loyaltyPoints;
    }

    // cart
    public UpdateCustomerReq(Long userId, List<Long> cartProductIds, List<Integer> cartProductQuantities, List<List<Long>> cartComputerSetsPartIds, List<Integer> cartComputerSetsQuantities) {
        this.userId = userId;
        this.cartProductIds = cartProductIds;
        this.cartProductQuantities = cartProductQuantities;
        this.cartComputerSetsPartIds = cartComputerSetsPartIds;
        this.cartComputerSetsQuantities = cartComputerSetsQuantities;
    }

    // current computer build product
    public UpdateCustomerReq(Long userId, List<Long> currComputerBuildComputerPartIds) {
        this.userId = userId;
        this.currComputerBuildComputerPartIds = currComputerBuildComputerPartIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public List<Long> getCartProductIds() {
        return cartProductIds;
    }

    public void setCartProductIds(List<Long> cartProductIds) {
        this.cartProductIds = cartProductIds;
    }

    public List<Integer> getCartProductQuantities() {
        return cartProductQuantities;
    }

    public void setCartProductQuantities(List<Integer> cartProductQuantities) {
        this.cartProductQuantities = cartProductQuantities;
    }

    public List<List<Long>> getCartComputerSetsPartIds() {
        return cartComputerSetsPartIds;
    }

    public void setCartComputerSetsPartIds(List<List<Long>> cartComputerSetsPartIds) {
        this.cartComputerSetsPartIds = cartComputerSetsPartIds;
    }

    public List<Integer> getCartComputerSetsQuantities() {
        return cartComputerSetsQuantities;
    }

    public void setCartComputerSetsQuantities(List<Integer> cartComputerSetsQuantities) {
        this.cartComputerSetsQuantities = cartComputerSetsQuantities;
    }

    public List<Long> getCurrComputerBuildComputerPartIds() {
        return currComputerBuildComputerPartIds;
    }

    public void setCurrComputerBuildComputerPartIds(List<Long> currComputerBuildComputerPartIds) {
        this.currComputerBuildComputerPartIds = currComputerBuildComputerPartIds;
    }
    
}
