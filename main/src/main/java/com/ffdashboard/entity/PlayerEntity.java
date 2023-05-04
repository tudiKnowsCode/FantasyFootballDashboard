package com.ffdashboard.entity;

public class PlayerEntity {

    private String id;
    
    private String name;

    private String position;

    private Integer value; 

    public PlayerEntity(String id, String name, String position, Integer value) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.value = value;
    }
        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }
        
        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
        
        public void setPosition(String position) {
            this.position = position;
        }

        public String getPosition() {
            return this.position;
        }
        
        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return this.value;
        }
    

}
