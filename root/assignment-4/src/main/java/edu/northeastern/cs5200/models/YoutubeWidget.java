package edu.northeastern.cs5200.models;

import javax.persistence.*;

//@Entity
public class YoutubeWidget extends Widget {
  private String youTubeId;

  public String getYouTubeId() {
    return youTubeId;
  }

  public void setYouTubeId(String youTubeId) {
    this.youTubeId = youTubeId;
  }

  public YoutubeWidget() {
    super();
  }
}
