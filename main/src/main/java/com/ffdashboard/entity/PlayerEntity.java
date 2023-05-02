package com.ffdashboard.entity;

public class PlayerEntity {

    private Integer id;
    
    private String name;

    private String position;

    public PlayerEntity(Integer id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }
        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getId() {
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

}
