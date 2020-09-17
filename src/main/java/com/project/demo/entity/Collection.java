package com.project.demo.entity;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String topic;

    private String imageURL;

    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "collection", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Item> items;

    @Transient
    private long itemsNumber;

    public Collection() {
    }

    public Collection(String name, String topic, String imageURL, String description) {
        this.name = name;
        this.topic = topic;
        this.imageURL = imageURL;
        this.description = description;
    }

    public Collection(long id,String name, String topic, String imageURL, String description, User user, Set<Item> items, long itemsNumber) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.imageURL = imageURL;
        this.description = description;
        this.user = user;
        this.items = items;
        this.itemsNumber = itemsNumber;
    }

    public Set<Item> getItems() {
        return items;
    }

    public long getUserId() {
        return user.getId();
    }

    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }

//    @JsonIgnore
//    public User getUser() {
//        return user;
//    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getItemsNumber() {
        return itemsNumber;
    }

    public void setItemsNumber(long itemsNumber) {
        this.itemsNumber = itemsNumber;
    }
}
