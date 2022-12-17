package com.p1.application.data;

// TODO: Auto-generated Javadoc
/**
 * The Class Regions.
 */
public class Regions {
    
    /** The id. */
    private int id;
    
    /** The name. */
    private String name;
    
    /**
     * Instantiates a new regions.
     *
     * @param id the id
     * @param name the name
     */
    public Regions(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
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
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
}
