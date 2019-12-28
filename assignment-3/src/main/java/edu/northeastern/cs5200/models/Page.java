package edu.northeastern.cs5200.models;

public class Page {
  private int id;
  private String title;
  private String description;
  private String created;
  private String updated;
  private int views;
  private int websiteId;

  public Page() {
  }

  public Page(int id, String title, String description, String created, String updated, int views) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.created = created;
    this.updated = updated;
    this.views = views;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getUpdated() {
    return updated;
  }

  public void setUpdated(String updated) {
    this.updated = updated;
  }

  public int getViews() {
    return views;
  }

  public void setViews(int views) {
    this.views = views;
  }

  public int getWebsiteId() {
    return websiteId;
  }

  public void setWebsiteId(int websiteId) {
    this.websiteId = websiteId;
  }

  @Override
  public String toString() {
    return "id: " + this.getId() + ", title: " + this.getTitle() + ", description: "
            + this.getDescription() + ", created: " + this.getCreated() + ", updated: "
            + this.getUpdated() + ", views: " + this.getViews();
  }
}
