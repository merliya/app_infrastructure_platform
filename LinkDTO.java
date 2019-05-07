package com.jbhunt.infrastructure.platform.dto;

public class LinkDTO {
  private String link;
  private String name;
  private String text;
  private String title;
  private Boolean favorite;

  public LinkDTO() {
  }

  public LinkDTO(String link, String name, String text, String title, Boolean favorite) {
    this.link = link;
    this.name = name;
    this.text = text;
    this.title = title;
    this.favorite = favorite;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getFavorite() {
    return favorite;
  }

  public void setFavorite(Boolean favorite) {
    this.favorite = favorite;
  }
}
