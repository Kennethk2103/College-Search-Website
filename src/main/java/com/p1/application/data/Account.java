package com.p1.application.data;


import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * The Class Account.
 */
@EntityScan
public class Account {
    
    /** The id. */
    private int id;
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /** The Username. */
    private String Username;
    
    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return Username;
    }
    
    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        Username = username;
    }
    
    /** The password. */
    private String password;
    
    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /** The Email. */
    private String Email;
    
    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return Email;
    }
    
    /** The favorites. */
    private String favorites;
    
    /**
     * Instantiates a new account.
     *
     * @param id the id
     * @param username the username
     * @param password the password
     * @param email the email
     * @param favorites the String of favorites id
     */
    public Account(int id, String username, String password, String email, String favorites) {
        this.id = id;
        Username = username;
        this.password = password;
        Email = email;
        this.favorites = favorites;
    }
    
    /**
     * Instantiates a new account.
     *
     * @param email the email
     * @param password the password
     */
    public Account(String email,String password){
        Username= email.substring(0,email.indexOf("@"));
        this.Email=email;
        this.password=password;
        favorites="";
    }
    
    /**
     * Gets the favorites.
     *
     * @return the favorites
     */
    public String getFavorites() {
        return favorites;
    }
    
    /**
     * Sets the favorites.
     *
     * @param favorites the new favorites
     */
    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    /** The Zip. */
    private Integer Zip;
    
    /**
     * Gets the zip.
     *
     * @return the zip
     */
    public Integer getZip() {
        return Zip;
    }
    
    /**
     * Sets the zip.
     *
     * @param zip the new zip
     */
    public void setZip(Integer zip) {
        Zip = zip;
    }
    
    /** The gpa. */
    private Double GPA;
    
    /**
     * Gets the gpa.
     *
     * @return the gpa
     */
    public Double getGPA() {
        return GPA;
    }
    
    /**
     * Sets the gpa.
     *
     * @param gPA the new gpa
     */
    public void setGPA(Double gPA) {
        GPA = gPA;
    }
    
    /** The sat. */
    private Double SAT;
    
    /**
     * Gets the sat.
     *
     * @return the sat
     */
    public Double getSAT() {
        return SAT;
    }
    
    /**
     * Sets the sat.
     *
     * @param sAT the new sat
     */
    public void setSAT(Double sAT) {
        SAT = sAT;
    }
    
    /** The act. */
    private Double ACT;
    
    /**
     * Gets the act.
     *
     * @return the act
     */
    public Double getACT() {
        return ACT;
    }
    
    /**
     * Sets the act.
     *
     * @param aCT the new act
     */
    public void setACT(Double aCT) {
        ACT = aCT;
    }
    
    /** The file with essay. */
    private byte[] file;
    
    /**
     * Gets the file with essay.
     *
     * @return the file
     */
    public byte[] getFile() {
        return file;
    }
    
    /**
     * Sets the file with essay.
     *
     * @param file the new file
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    
}
