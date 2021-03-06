package com.project.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private String imageURL;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Column(name = "creation_date")
    private long creationDate;

    @Column(name = "likes_number")
    private long likesNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> usersWhoLiked = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "item_tag",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<Comment> comments;

    public Item() {
    }

    public Item(String name, String description, String imageURL) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", collection=" + collection +
                '}';
    }

    public List<Long> getUsersWhoLikedIds() {
        List<Long> usersWhoLikedIds = new ArrayList<>();
        for (User user : usersWhoLiked) {
            usersWhoLikedIds.add(user.getId());
        }
        return usersWhoLikedIds;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonIgnore
    public Set<User> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    @JsonIgnore
    public void setUsersWhoLiked(Set<User> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
    }

    public long getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(long likesNumber) {
        this.likesNumber = likesNumber;
    }

    public long getDate() {
        return creationDate;
    }

    public void setDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public long getCollectionId() {
        return collection.getId();
    }

    @JsonIgnore
    public Collection getCollection() {
        return collection;
    }

    @JsonIgnore
    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
